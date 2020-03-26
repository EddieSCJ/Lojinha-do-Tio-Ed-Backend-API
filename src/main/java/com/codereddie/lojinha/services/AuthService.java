package com.codereddie.lojinha.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.codereddie.lojinha.domain.Client;
import com.codereddie.lojinha.repository.ClientRepository;
import com.codereddie.lojinha.services.exceptions.ObjectNotFoundException;

@Service
public class AuthService {

	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private BCryptPasswordEncoder encrypt;
	
	@Autowired
	private EmailService emailService;
	
	
	public void sendNewPassword(String email) {
		Client client = clientRepository.findByEmail(email);
		if(client == null) {
			throw new ObjectNotFoundException("Email inexistente");
		}
		
		String newPass = newPassword();
		client.setPassword(encrypt.encode(newPass));
		clientRepository.save(client);
		emailService.sendNewPasswordEmail(client, newPass);
		
		
	}


	private String newPassword() {
		// TODO Auto-generated method stub
		char vet[] = new char[10];
		for (int i = 0; i < vet.length; i++) {
			vet[i] = (char) new Random().nextInt(10);
		}
		return new String(vet);
	}
}
