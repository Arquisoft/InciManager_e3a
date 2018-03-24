package uo.asw.inciManager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import uo.asw.inciManager.service.AgentService;

@Controller
public class AgentController {
	
	@Autowired
	private AgentService agentsService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginGet(Model model) {
		return "login";
	}

	@RequestMapping(value = { "/home" }, method = RequestMethod.GET)
	public String home(Model model) {
		return "home";
	}

	@RequestMapping(value = { "/login" }, method = RequestMethod.POST)
	public String loginPost(Model model, @RequestParam String username, @RequestParam String password,
			@RequestParam String kind) {
		String idAgente = agentsService.communicationAgents(username, password, kind);
		if(idAgente != null) {
			agentsService.setIdConnected(idAgente);
			return "redirect:/home";
		}
		return "/login";
	}
}
