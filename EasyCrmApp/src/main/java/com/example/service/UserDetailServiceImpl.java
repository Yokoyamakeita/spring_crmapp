package com.example.service;

import java.util.HashSet;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.model.SiteUser;
import com.example.repository.SiteUserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
//処理
@Service 
public class UserDetailServiceImpl implements UserDetailsService{

	private final SiteUserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		
		
		var user = userRepository.findByUsername(username);
		
		if(user == null) {
			throw new UsernameNotFoundException(username+"not found");
		}
		return createUserDetails(user);
	}
	
	public User createUserDetails(SiteUser user) {
		
		var grantedAuthorities = new HashSet<GrantedAuthority>();
		
		grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_"+user.getRole()));
		
		return new User(user.getUsername(),user.getPassword(),grantedAuthorities);
	}

}
