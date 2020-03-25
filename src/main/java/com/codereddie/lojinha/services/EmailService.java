package com.codereddie.lojinha.services;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.codereddie.lojinha.domain.Orderr;

@Service
public interface EmailService {

	void sendOrderConfirmationEmail(Orderr order);
	
	void sendEmail(SimpleMailMessage message);

	
}
