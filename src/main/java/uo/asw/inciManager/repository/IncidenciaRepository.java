package uo.asw.inciManager.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import uo.asw.dbManagement.model.Incidencia;

public interface IncidenciaRepository extends CrudRepository<Incidencia, Long>{

	
	@Query("SELECT i FROM Incidencia i WHERE i.idAgente = ?1 ORDER BY i.id ASC ")
	Page<Incidencia> findIncidenciasByIdAgent(Pageable pageable, String id);
}
