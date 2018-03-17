package uo.asw.iniManager.service;


import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class InsertSampleDataService {
	
	@Autowired
	private IncidenciasService incidenciasService;
	
	
	@PostConstruct
	public void init() {
		
		

	}

}
