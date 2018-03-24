package uo.asw.inciManager.controller;

import java.security.Principal;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

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
import org.springframework.web.context.request.WebRequest;

import uo.asw.dbManagement.model.Agente;
import uo.asw.dbManagement.model.Categoria;
import uo.asw.dbManagement.model.Incidencia;
import uo.asw.dbManagement.model.Propiedad;
import uo.asw.dbManagement.tipos.CategoriaTipos;
import uo.asw.dbManagement.tipos.PropiedadTipos;
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
			@RequestParam("category") String category, WebRequest webRequest) {
		
		Categoria categoria = new Categoria(CategoriaTipos.valueOf(category), incidencia);
		String emailAgente = principal.getName();
		Agente agente = agenteService.getAgentByIdentificador(emailAgente);
		incidencia.setPropiedades(obtainProperties(incidencia, webRequest));
		
		incidenciasService.createNewIncidencia(incidencia, categoria, agente);
		
		return "redirect:/incidencia/list";
	}

	/**
	 * Método que devuelve una coleccion de las propiedades introducidas
	 * @param incidencia la incidencia
	 * @param webRequest
	 * @return set con todas las propiedades introducidas.
	 */
	private Set<Propiedad> obtainProperties(Incidencia incidencia, WebRequest webRequest) {
		Set<Propiedad> propiedades = new HashSet<Propiedad>();
		
		String drivinV = webRequest.getParameter("drivinVelocity");
		String windV = webRequest.getParameter("windVelocity");
		String preasure = webRequest.getParameter("preasure");
		String humedad = webRequest.getParameter("humedad");
		String temperature = webRequest.getParameter("temperature");
		
		if (drivinV!=null) {
			propiedades.add(new Propiedad(PropiedadTipos.valueOf("VELOCIDAD_CIRCULACION"), incidencia, Double.valueOf(drivinV)));
		} if (windV!=null) {
			propiedades.add(new Propiedad(PropiedadTipos.valueOf("VELOCIDAD_VIENTO"), incidencia, Double.valueOf(windV)));
		} if (preasure!=null) {
			propiedades.add(new Propiedad(PropiedadTipos.valueOf("PRESION"), incidencia, Double.valueOf(preasure)));
		} if (humedad!=null) {
			propiedades.add(new Propiedad(PropiedadTipos.valueOf("HUMEDAD"), incidencia, Double.valueOf(humedad)));
		} if (temperature!=null) {
			propiedades.add(new Propiedad(PropiedadTipos.valueOf("TEMPERATURA"), incidencia, Double.valueOf(temperature)));
		}
		
		return propiedades;
	}
	
}
