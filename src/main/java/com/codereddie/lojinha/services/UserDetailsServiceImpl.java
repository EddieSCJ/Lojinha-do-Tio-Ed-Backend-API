package com.codereddie.lojinha.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.codereddie.lojinha.domain.Client;
import com.codereddie.lojinha.repository.ClientRepository;
import com.codereddie.lojinha.security.UserSS;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	ClientRepository clientRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Client client = clientRepository.findByEmail(username);
		if(client == null) {
			throw new UsernameNotFoundException(username);
		}
		
		return new UserSS(client.getId(), client.getEmail(), client.getPassword(), client.getProfiles());
	}

}
