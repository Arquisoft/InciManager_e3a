package uo.asw.inciManager.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import uo.asw.dbManagement.model.Agente;

public interface AgenteRespository extends CrudRepository<Agente, Long>{
	
	@Query("SELECT a FROM Agente a WHERE a.nombre=?1 and a.contrasena=?2 and a.kindCode=?3")
	Agente findeAgentByUserPassKind(String user, String password, String kindCode);

	@Query("SELECT a FROM Agente a WHERE a.identificador=?1")
	Agente findByIdentificador(String identificador);
}
