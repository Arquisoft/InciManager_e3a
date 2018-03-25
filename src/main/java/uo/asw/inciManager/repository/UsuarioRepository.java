package uo.asw.inciManager.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import uo.asw.dbManagement.model.Usuario;
@Repository
public interface UsuarioRepository extends MongoRepository<Usuario, ObjectId> {

	
}
