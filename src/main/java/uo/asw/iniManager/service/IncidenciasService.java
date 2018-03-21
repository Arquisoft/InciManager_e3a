package uo.asw.iniManager.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import uo.asw.dbManagement.model.Agente;
import uo.asw.dbManagement.model.Incidencia;
import uo.asw.inciManager.repository.IncidenciaRepository;
import uo.asw.inciManager.util.DateUtil;
import uo.asw.kafka.producers.KafkaProducer;

@Service
public class IncidenciasService {

	@Autowired
	private IncidenciaRepository incidenciasRepository;
	
	@Autowired
	private AgenteService agenteService;
	
	//@Autowired //la incidencia va a kafka
    //private KafkaProducer kafkaProducer;
	
	/**
	 * Carga la indicencia que se recibe en formato JSON
	 * @param datosInci recibe JSON con los diferentes datos de la incidencia
	 * @return null y error si no se procesa bien el contenido, en caso contrario
	 * el nombre de la incidencia y la respuesta correcta
	 */
	public ResponseEntity<String> cargarIncidencia(Map<String, Object> datosInci) {
		String user = (String)datosInci.get("usuario");
		String password = (String)datosInci.get("contrasena");
		String kindCode = (String)datosInci.get("kindCode");
		Agente agente = agenteService.getAgente(user, password, kindCode);
		Incidencia incidencia = null;
		if (validarIncidencia((String)datosInci.get("nombreIncidencia"), agente)) {
				if(agente.getPermisoEnvio().equals("si")) {
					incidencia = crearIncidencia(datosInci, agente);	
					incidenciasRepository.save(incidencia);
					//this.kafkaProducer.send("pruebas_am", incidencia.getDescripcion());
					return new ResponseEntity<String>(incidencia.getNombreIncidencia(), HttpStatus.OK);
			}
		}
		return new ResponseEntity<String>("No aceptada", HttpStatus.NOT_ACCEPTABLE);
	}


	/**
	 * Valida el que el nombre de la incidencia no es nullo, no es un blanco y que el agente
	 * que la envia existe.
	 * @param nombreIncidencia que se recibe en el JSON
	 * @param agente Agente que ha enviado la incidencia que puede ser null
	 * @return true si la incidencia es valida y si el agente existe
	 */
	private boolean validarIncidencia(String nombreIncidencia, Agente agente) {
		return nombreIncidencia != null 
				&& !(nombreIncidencia).equals("") && agente != null;
	}
	

	/**
	 * Crea y devuelve el objeto incidencia
	 * @param datosInci los datos de la incidencia del JSON
	 * @param agente que envia la indicencia
	 * @return el objeto incidencia creado
	 */
	private Incidencia crearIncidencia(Map<String, Object> datosInci, Agente agente) {
		return new Incidencia((String)datosInci.get("nombreIncidencia"),
				(String)datosInci.get("descripcion"), 
				(String)datosInci.get("latitud"),
				(String)datosInci.get("longitud"), 
				DateUtil.stringToDate((String)datosInci.get("fechaEntrada")),
				DateUtil.stringToDate((String)datosInci.get("fechaCaducidad")),
				agente,
				(String)datosInci.get("propiedades"),
				(String)datosInci.get("categorias")
				);
	}
	
	public void addIncidencia(Incidencia inci) {
		incidenciasRepository.save(inci);
	}


	public Page<Incidencia> getIncidencias(Pageable pageable, Long id_agente) {
		return incidenciasRepository.findIncidenciasByIdAgent(pageable, id_agente);
	}
}
