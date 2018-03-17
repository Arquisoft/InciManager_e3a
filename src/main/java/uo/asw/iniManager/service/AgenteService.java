package uo.asw.iniManager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uo.asw.dbManagement.model.Agente;
import uo.asw.inciManager.repository.AgenteRespository;

@Service
public class AgenteService {

	@Autowired 
	private AgenteRespository agenteRepository;
	
	public Agente getAgente(String user, String password, String kindCode) {
		return agenteRepository.findeAgentByUserPassKind(user, password, kindCode);
	}
	
	
	//Este metodo es solo para añadir a los agentes de prueba que se crear en InsertSampleDataService
	public void addAgente(Agente agent) {
		agenteRepository.save(agent);
	}
}
