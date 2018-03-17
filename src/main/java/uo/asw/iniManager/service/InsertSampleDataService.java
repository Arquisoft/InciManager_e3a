package uo.asw.iniManager.service;


import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uo.asw.dbManagement.model.Agente;


@Service
public class InsertSampleDataService {
	
	@Autowired
	private AgenteService agenteService;	
	
	@PostConstruct
	public void init() {
		Agente agente1 = new Agente("Agente1", "123456", "1", "Id1", "0001", "0001", "agente1@prueba.es","si");
		Agente agente2 = new Agente("Agente2", "123456", "2", "Id2", "0002", "0002", "agente2@prueba.es","si");
		Agente agente3 = new Agente("Agente3", "123456", "3", "Id3", "0003", "0003", "agente3@prueba.es","si");
		Agente agente4 = new Agente("Agente4", "123456", "4", "Id4", "0004", "0004", "agente4@prueba.es","si");
		Agente agente5 = new Agente("Agente5", "123456", "5", "Id5", "0005", "0005", "agente5@prueba.es","si");
		agenteService.addAgente(agente1);
		agenteService.addAgente(agente2);
		agenteService.addAgente(agente3);
		agenteService.addAgente(agente4);
		agenteService.addAgente(agente5);
	}

}
