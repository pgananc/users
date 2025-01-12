package cl.bci.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import cl.bci.repository.IUserRepo;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private final String PASSWORD = "123";
	@Autowired
	private IUserRepo repo;
	@Autowired
	private BCryptPasswordEncoder bcrypt;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Object[] objetUsuario = (Object[]) repo.findOneByName(username);
		cl.bci.model.User usuario = new cl.bci.model.User();
		if (objetUsuario == null) {
			throw new UsernameNotFoundException(String.format("Usuario no existe", username));
		} else {
			usuario.setName((String) objetUsuario[0]);
			usuario.setPassword((String) objetUsuario[1]);
		}

		List<GrantedAuthority> roles = new ArrayList<>();

		roles.add(new SimpleGrantedAuthority("admin"));

		return new User(usuario.getName(), bcrypt.encode(PASSWORD), roles);
	}

}
