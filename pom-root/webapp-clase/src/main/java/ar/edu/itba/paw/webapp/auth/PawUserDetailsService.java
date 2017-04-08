package ar.edu.itba.paw.webapp.auth;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import ar.edu.itba.paw.User;
import ar.edu.itba.paw.service.UserService;

@Component
public class PawUserDetailsService implements UserDetailsService {

	@Autowired
	private UserService us;
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		final User user = us.findByName(username);
		
		if (user == null)
			throw new UsernameNotFoundException("No user by the name " + username);
		
		final Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
	}

}
