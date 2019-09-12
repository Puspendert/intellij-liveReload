package com.sample.rest.security;

import com.sample.persistence.User;
import com.sample.persistence.UserDao;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service("userDetailsService")
@Transactional
public class MyUserDetailsService implements UserDetailsService {

	
	private UserDao userDao;
	
	public MyUserDetailsService(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user =  userDao.findByEmail(username);

		Set<GrantedAuthority> userAuthorities = new HashSet<>();

		return buildAuthenticatedUser(user, userAuthorities );

	}
	
	private org.springframework.security.core.userdetails.User buildAuthenticatedUser(User user, Collection<? extends GrantedAuthority> authorities){
	    String username = user.getEmail();
	    String password = user.getPassword();
	    String uniqueId = user.getUniqueId();
	    int userId = user.getUserId();
	    boolean enabled = true;
	    boolean accountNonExpired = true;
	    boolean credentialsNonExpired = true;
	    boolean accountNonLocked = true;
	    
	    AuthenticatedUser authenticatedUser =  new AuthenticatedUser(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
	    authenticatedUser.setUniqueId(uniqueId);
	    authenticatedUser.setUserId(userId);
	 
	    
	    return authenticatedUser;
	
	}

//	private Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles) {
//
//		Set<GrantedAuthority> userAuthorities = new HashSet<>();
//		roles.forEach((role) -> role.getPrivileges()
//				.forEach((privilege) -> userAuthorities.add(new SimpleGrantedAuthority(privilege.getName()))));
//
//		return userAuthorities;
//	}

}
