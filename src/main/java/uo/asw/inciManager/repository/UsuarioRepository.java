package uo.asw.inciManager.repository;

import org.springframework.data.repository.CrudRepository;

import uo.asw.dbManagement.model.Agente;
import uo.asw.dbManagement.model.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

	
}
