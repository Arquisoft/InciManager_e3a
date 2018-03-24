package uo.asw.inciManager.controller;

import java.security.Principal;
import java.util.LinkedList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import uo.asw.dbManagement.model.Incidencia;
import uo.asw.inciManager.service.AgentService;
import uo.asw.inciManager.service.IncidenciasService;

@Controller
public class IncidenciasController {
	
	@Autowired
	private IncidenciasService incidenciasService;
	
	@Autowired
	private AgentService agentService;
	
	@RequestMapping("/incidencia/list" )
	public String getListado(Model model, Pageable pageable){
		Page<Incidencia> incidencias = new PageImpl<Incidencia>(new LinkedList<Incidencia>());
		incidencias = incidenciasService.getIncidencias(pageable, agentService.getIdConnected());
		model.addAttribute("incidenciasList", incidencias.getContent() );
		model.addAttribute("page", incidencias);
		return "incidencia/list";
	}
	
	@RequestMapping("/incidencia/create")
	public String create() {
		return "incidencia/create";
	}
	
	@RequestMapping(value="/incidencia/create", method = RequestMethod.POST)
	public String createNewIncidence() {
		return "incidencia/list";
	}
	
	@RequestMapping(value = "/inidencia/send", method = RequestMethod.POST)
	public ResponseEntity<String> update(@RequestBody Map<String, Object> datosInci) {
	   return incidenciasService.cargarIncidencia(datosInci);
	}

}
