package uo.asw.inciManager.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import uo.asw.iniManager.service.IncidenciasService;

/**
 * Clase que recibe una petición POST en formato
 * en JSON y crea una Incidencia con esos datos
 * 
 * @version marzo 2018
 */
@RestController
public class CargarIncidenciasController {
	
	@Autowired
	private IncidenciasService incidenciasService;
	
	@RequestMapping(value = "/inci", method = RequestMethod.POST)
	public ResponseEntity<String> update(@RequestBody Map<String, Object> datosInci) {
	   return incidenciasService.cargarIncidencia(datosInci);
	}
	
}
