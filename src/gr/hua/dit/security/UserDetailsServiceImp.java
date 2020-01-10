package gr.hua.dit.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gr.hua.dit.dao.UserDAO;
import gr.hua.dit.entities.User;

@Service("userDetailsService")
public class UserDetailsServiceImp implements UserDetailsService {

	@Autowired
	private UserDAO userDetailsDao;

	@Transactional(readOnly = true)
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userDetailsDao.findByUserName(username);

		UserBuilder builder = null;
		if (user != null) {

			builder = org.springframework.security.core.userdetails.User.withUsername(username);
			builder.disabled(!user.isEnabled());
			builder.password(user.getPassword());
			String[] authorities = user.getAuthorities().stream().map(a -> a.getAuthority()).toArray(String[]::new);
			
			builder.authorities(authorities);
		} else {
			throw new UsernameNotFoundException("User not found.");
		}

		return builder.build();
	}
}
