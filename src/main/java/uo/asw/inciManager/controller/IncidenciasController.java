package uo.asw.inciManager.controller;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

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
import org.springframework.web.context.request.WebRequest;

import uo.asw.dbManagement.model.Categoria;
import uo.asw.dbManagement.model.Incidencia;
import uo.asw.dbManagement.model.Propiedad;
import uo.asw.dbManagement.tipos.CategoriaTipos;
import uo.asw.dbManagement.tipos.PropiedadTipos;
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
	public String createNewIncidence(Incidencia incidencia, 
			@RequestParam("category") String category, WebRequest webRequest) {
		
		Categoria categoria = new Categoria(CategoriaTipos.valueOf(category));
		incidencia.setPropiedades(obtainProperties(incidencia, webRequest));
		
		incidenciasService.createNewIncidencia(incidencia, categoria, agentService.getIdConnected());
		
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
			propiedades.add(new Propiedad(PropiedadTipos.valueOf("VELOCIDAD_CIRCULACION"), Double.valueOf(drivinV)));
		} if (windV!=null) {
			propiedades.add(new Propiedad(PropiedadTipos.valueOf("VELOCIDAD_VIENTO"),  Double.valueOf(windV)));
		} if (preasure!=null) {
			propiedades.add(new Propiedad(PropiedadTipos.valueOf("PRESION"), Double.valueOf(preasure)));
		} if (humedad!=null) {
			propiedades.add(new Propiedad(PropiedadTipos.valueOf("HUMEDAD"),Double.valueOf(humedad)));
		} if (temperature!=null) {
			propiedades.add(new Propiedad(PropiedadTipos.valueOf("TEMPERATURA"), Double.valueOf(temperature)));
		}
		
		return propiedades;
	}
	
	@RequestMapping(value = "/inci", method = RequestMethod.POST)
	public ResponseEntity<String> update(@RequestBody Map<String, Object> datosInci) {
	   return incidenciasService.cargarIncidencia(datosInci);
	}

}
