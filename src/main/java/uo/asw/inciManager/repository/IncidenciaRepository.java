package uo.asw.inciManager.repository;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import uo.asw.dbManagement.model.Incidencia;
@Repository
public interface IncidenciaRepository extends MongoRepository<Incidencia, ObjectId>{

	Page<Incidencia> findByIdAgente(String idAgente, Pageable pageable);
}
