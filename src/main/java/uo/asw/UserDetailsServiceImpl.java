package uo.asw;



import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{


	@Override
	public UserDetails loadUserByUsername(String identificador) throws UsernameNotFoundException {

//		Agente agente = agenteRespository.findByIdentificador(identificador);
//		if(agente != null){
//			Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
//
//			return new org.springframework.security.core.userdetails.User( 
//					agente.getEmail(), agente.getContrasena(), grantedAuthorities);
//		}throw new UsernameNotFoundException(identificador);
		return null;
	}

}
