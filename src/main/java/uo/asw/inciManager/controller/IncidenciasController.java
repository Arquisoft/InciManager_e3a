package uo.asw.inciManager.controller;

import java.security.Principal;
import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import uo.asw.dbManagement.model.Agente;
import uo.asw.dbManagement.model.Incidencia;
import uo.asw.iniManager.service.AgenteService;
import uo.asw.iniManager.service.IncidenciasService;

@Controller
public class IncidenciasController {
	
	@Autowired
	private IncidenciasService incidenciasService;
	
	@Autowired
	private AgenteService agenteService;
	
	@RequestMapping("/incidencia/list" )
	public String getListado(Model model, Pageable pageable, Principal principal){
		
		String emailAgente = principal.getName();
		Agente agent = agenteService.getAgentByEmail(emailAgente);
		
		Page<Incidencia> incidencias = new PageImpl<Incidencia>(new LinkedList<Incidencia>());
		
		incidencias = incidenciasService.getIncidencias(pageable, agent.getId());
		
		model.addAttribute("incidenciasList", incidencias.getContent() );
		model.addAttribute("nameAgent", "Incidencias de "+agent.getNombre());
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

}
