package uo.asw.inciManager.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import uo.asw.dbManagement.model.Usuario;

public interface UsuarioRepository extends MongoRepository<Usuario, ObjectId> {

	
}
