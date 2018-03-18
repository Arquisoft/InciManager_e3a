package uo.asw.inciManager.controller;

import java.security.Principal;
import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


import uo.asw.dbManagement.model.Incidencia;
import uo.asw.iniManager.service.AgenteService;
import uo.asw.iniManager.service.IncidenciasService;

@Controller
public class IncidenciasController {
	
	@Autowired
	private IncidenciasService incidenciasService;
	
	@Autowired
	private AgenteService agenteService;
	
	@RequestMapping("/instacia/list/{id_agente}" )
	public String getListado(Model model, Pageable pageable, @PathVariable Long id_agente){
		
		String nombreAgente = agenteService.getAgenteById(id_agente).getNombre();
		
		Page<Incidencia> incidencias = new PageImpl<Incidencia>(new LinkedList<Incidencia>());
		
		incidencias = incidenciasService.getIncidencias(pageable, id_agente);
		
		model.addAttribute("incidenciasList", incidencias.getContent() );
		model.addAttribute("nameAgent", "Incidencias de "+nombreAgente);
		model.addAttribute("page", incidencias);
		
		return "instacia/list";
	}

}
