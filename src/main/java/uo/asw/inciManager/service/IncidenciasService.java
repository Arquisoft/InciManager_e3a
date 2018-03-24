package uo.asw.inciManager.service;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import uo.asw.dbManagement.model.Incidencia;
import uo.asw.inciManager.repository.IncidenciaRepository;
import uo.asw.inciManager.util.DateUtil;
import uo.asw.kafka.producers.KafkaProducer;

@Service
public class IncidenciasService {

	@Autowired
	private IncidenciaRepository incidenciasRepository;

	@Autowired
	private KafkaProducer kafkaProducer;

	/**
	 * Carga la indicencia que se recibe en formato JSON
	 * 
	 * @param datosInci
	 *            recibe JSON con los diferentes datos de la incidencia
	 * @return null y error si no se procesa bien el contenido, en caso contrario el
	 *         nombre de la incidencia y la respuesta correcta
	 */
	public ResponseEntity<String> cargarIncidencia(Map<String, Object> datosInci) {
		String login = (String) datosInci.get("login");
		String password = (String) datosInci.get("password");
		String kind = (String) datosInci.get("kind");
		Long idAgente = communicationAgents(login, password, kind);
		Incidencia incidencia = null;
		if (validarIncidencia((String) datosInci.get("nombreIncidencia"), idAgente)) {
			incidencia = crearIncidencia(datosInci, idAgente);
			incidenciasRepository.save(incidencia);
			this.kafkaProducer.send("incidenciasTopic", incidencia.getDescripcion());
			return new ResponseEntity<String>(incidencia.getNombreIncidencia(), HttpStatus.OK);
		}
		 return new ResponseEntity<String>("No aceptada", HttpStatus.NOT_ACCEPTABLE);
	}

	/**
	 * Valida el que el nombre de la incidencia no es nullo, no es un blanco y que
	 * el agente que la envia existe.
	 * 
	 * @param nombreIncidencia
	 *            que se recibe en el JSON
	 * @param agente
	 *            Agente que ha enviado la incidencia que puede ser null
	 * @return true si la incidencia es valida y si el agente existe
	 */
	private boolean validarIncidencia(String nombreIncidencia, Long idAgente) {
		return nombreIncidencia != null && !(nombreIncidencia).equals("") && idAgente != null;
	}

	/**
	 * Metodo que sirve de comunicacion con agents , se le envia el usuario, login y pass
	 * y devuelve el id del usuario.
	 * @param login
	 * @param password
	 * @param kind
	 * @return
	 */
	public Long communicationAgents(String login, String password, String kind) {
		String urlAgents = "http://localhost:8091/user";
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("login", login);
		map.put("password", password);
		map.put("kind", kind);
		HttpEntity<Map<String, Object>> request = new HttpEntity<Map<String, Object>>(map, headers);
		ResponseEntity<String> response = restTemplate.postForEntity(urlAgents, request, String.class);
		JSONObject json;
		if (response.getStatusCode() == HttpStatus.OK) {
			try {
				json = new JSONObject(response.getBody());
				System.out.println(json.getLong("id"));
				return json.getLong("id");
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * Crea y devuelve el objeto incidencia
	 * 
	 * @param datosInci
	 *            los datos de la incidencia del JSON
	 * @param agente
	 *            que envia la indicencia
	 * @return el objeto incidencia creado
	 */
	private Incidencia crearIncidencia(Map<String, Object> datosInci, Long idAgente) {
		return new Incidencia((String) datosInci.get("nombreIncidencia"), (String) datosInci.get("descripcion"),
				(String) datosInci.get("latitud"), (String) datosInci.get("longitud"),
				DateUtil.stringToDate((String) datosInci.get("fechaEntrada")),
				DateUtil.stringToDate((String) datosInci.get("fechaCaducidad")), idAgente,
				(String) datosInci.get("propiedades"), (String) datosInci.get("categorias"));
	}

	public void addIncidencia(Incidencia inci) {
		incidenciasRepository.save(inci);
	}

	public Page<Incidencia> getIncidencias(Pageable pageable, Long id_agente) {
		return incidenciasRepository.findIncidenciasByIdAgent(pageable, id_agente);
	}
}
