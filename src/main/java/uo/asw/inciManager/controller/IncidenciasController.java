package uo.asw.inciManager.controller;

import java.util.LinkedList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
		model.addAttribute("idAgente", agentService.getIdConnected());
		return "incidencia/list";
	}
	
	@RequestMapping("/incidencia/create")
	public String create(Model model) {
		model.addAttribute("incidencia", new Incidencia());
		model.addAttribute("idAgente", agentService.getIdConnected());
		return "incidencia/create";
	}
	
	@RequestMapping(value="/incidencia/create", method = RequestMethod.POST)
	public String createNewIncidence(@Validated Incidencia incidencia,
			@RequestParam("category") String category /*)@RequestParam("drivinVelocity") String dV,
			@RequestParam("windVelocity") String wV, @RequestParam("preasure") String p, 
			@RequestParam("humedad") String h, @RequestParam("temperature") String t*/) {

//		Categoria categoria = new Categoria(CategoriaTipos.valueOf(category), incidencia);
//		String emailAgente = principal.getName();
//		Agente agente = agenteService.getAgentByIdentificador(emailAgente);
//		
//		incidenciasService.createNewIncidencia(incidencia, categoria, agente);
		
		return "redirect:/incidencia/list";
	}
	
	@RequestMapping(value = "/inci", method = RequestMethod.POST)
	public ResponseEntity<String> update(@RequestBody Map<String, Object> datosInci) {
	   return incidenciasService.cargarIncidencia(datosInci);
	}

}
