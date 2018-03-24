package uo.asw;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService; import org.springframework.security.core.userdetails.UsernameNotFoundException; import org.springframework.stereotype.Service;

import uo.asw.dbManagement.model.Agente;
import uo.asw.inciManager.repository.AgenteRespository;

import java.util.*;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private AgenteRespository agenteRespository;

	@Override
	public UserDetails loadUserByUsername(String identificador) throws UsernameNotFoundException {

		Agente agente = agenteRespository.findByIdentificador(identificador);
		if(agente != null){
			Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

			return new org.springframework.security.core.userdetails.User( 
					agente.getIdentificador(), agente.getContrasena(), grantedAuthorities);
		}throw new UsernameNotFoundException(identificador);
	}

}
