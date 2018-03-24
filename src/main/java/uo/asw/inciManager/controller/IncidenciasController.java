package uo.asw.inciManager.controller;

import java.security.Principal;
import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import uo.asw.dbManagement.model.Agente;
import uo.asw.dbManagement.model.Categoria;
import uo.asw.dbManagement.model.Incidencia;
import uo.asw.dbManagement.tipos.CategoriaTipos;
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
		
		String identificador = principal.getName();
		Agente agent = agenteService.getAgentByIdentificador(identificador);
		
		Page<Incidencia> incidencias = new PageImpl<Incidencia>(new LinkedList<Incidencia>());
		
		incidencias = incidenciasService.getIncidencias(pageable, agent.getId());
		
		model.addAttribute("incidenciasList", incidencias.getContent() );
		model.addAttribute("nameAgent", "Incidencias de "+agent.getNombre());
		model.addAttribute("page", incidencias);
		
		return "incidencia/list";
	}
	
	@RequestMapping("/incidencia/create")
	public String create(Model model) {
		model.addAttribute("incidencia", new Incidencia());
		return "incidencia/create";
	}
	
	@RequestMapping(value="/incidencia/create", method = RequestMethod.POST)
	public String createNewIncidence(@Validated Incidencia incidencia, Principal principal,
			@RequestParam("category") String category /*)@RequestParam("drivinVelocity") String dV,
			@RequestParam("windVelocity") String wV, @RequestParam("preasure") String p, 
			@RequestParam("humedad") String h, @RequestParam("temperature") String t*/) {

		Categoria categoria = new Categoria(CategoriaTipos.valueOf(category), incidencia);
		String emailAgente = principal.getName();
		Agente agente = agenteService.getAgentByIdentificador(emailAgente);
		
		incidenciasService.createNewIncidencia(incidencia, categoria, agente);
		
		return "redirect:/incidencia/list";
	}

}
