package uo.asw.inciManager.controller;

import java.util.Date;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import uo.asw.dbManagement.model.Categoria;
import uo.asw.dbManagement.model.Incidencia;
import uo.asw.dbManagement.model.Propiedad;
import uo.asw.dbManagement.tipos.CategoriaTipos;
import uo.asw.dbManagement.tipos.EstadoTipos;
import uo.asw.dbManagement.tipos.PropiedadTipos;
import uo.asw.inciManager.util.DateUtil;

/**
 * Clase que recibe una petición POST en formato
 * en JSON y crea una Incidencia con esos datos
 * 
 * @version marzo 2018
 */
@RestController
public class RecibirIncidenciaController {
	
	@RequestMapping(value = "/inci", method = RequestMethod.POST)
	public ResponseEntity<String> update(@RequestBody Map<String, Object> datosInci) {
		Long idAgente = 0L; //sacar de BD con el usuario/pass de la inci
		HttpStatus respuesta;
		Incidencia incidencia = null;
		if ((String)datosInci.get("nombreIncidencia") != null 
				&& !((String)datosInci.get("nombreIncidencia")).equals("")) {
			/*
			incidencia = new Incidencia((String)datosInci.get("nombreIncidencia"),
					(String)datosInci.get("descripcion"), 
					(String)datosInci.get("latitud"),
					(String)datosInci.get("longitud"), 
					EstadoTipos.ABIERTA, 
					DateUtil.stringToDate((String)datosInci.get("fechaEntrada")),
					DateUtil.stringToDate((String)datosInci.get("fechaCaducidad")),
					idAgente,
					(String)datosInci.get("propiedades"),
					(String)datosInci.get("categorias")
					);	*/
					
			
			respuesta = HttpStatus.OK;
		}
		else
			respuesta = HttpStatus.NOT_ACCEPTABLE;

	   return new ResponseEntity<String>(incidencia.getNombreIncidencia(), respuesta);
	}
	
}
