package uo.asw.iniManager.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import uo.asw.dbManagement.model.Agente;
import uo.asw.dbManagement.model.Incidencia;
import uo.asw.dbManagement.tipos.EstadoTipos;
import uo.asw.inciManager.repository.IncidenciasRepository;
import uo.asw.inciManager.util.DateUtil;

@Service
public class IncidenciasService {

	@Autowired
	private IncidenciasRepository incidenciasRepository;
	
	public ResponseEntity<String> cargarIncidencia(Map<String, Object> datosInci) {
		Long idAgente = 0L; //sacar de BD con el usuario/pass de la inci
		Agente agente = new Agente();
		HttpStatus respuesta;
		Incidencia incidencia = null;
		if ((String)datosInci.get("nombreIncidencia") != null 
				&& !((String)datosInci.get("nombreIncidencia")).equals("")) {
			
			incidencia = new Incidencia((String)datosInci.get("nombreIncidencia"),
					(String)datosInci.get("descripcion"), 
					(String)datosInci.get("latitud"),
					(String)datosInci.get("longitud"), 
					EstadoTipos.ABIERTA, 
					DateUtil.stringToDate((String)datosInci.get("fechaEntrada")),
					DateUtil.stringToDate((String)datosInci.get("fechaCaducidad")),
					agente,
					(String)datosInci.get("propiedades"),
					(String)datosInci.get("categorias")
					);	
					
			
			respuesta = HttpStatus.OK;
		}
		else
			respuesta = HttpStatus.NOT_ACCEPTABLE;

	   return new ResponseEntity<String>(incidencia.getNombreIncidencia(), respuesta);
	}


	
}
