package uo.asw.inciManager.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import uo.asw.dbManagement.model.Propiedad;

public interface PropiedadRepository extends MongoRepository<Propiedad, ObjectId>{

}
