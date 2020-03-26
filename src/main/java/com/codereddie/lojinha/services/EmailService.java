package com.codereddie.lojinha.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.codereddie.lojinha.domain.Client;
import com.codereddie.lojinha.domain.Orderr;

@Service
public interface EmailService {

	void sendOrderConfirmationEmail(Orderr order);

	void sendEmail(SimpleMailMessage message);

	void sendOrderConfirmationHTMLEmail(Orderr order);

	void sendHTMLEmail(MimeMessage message);

	void sendNewPasswordEmail(Client client, String newPass);
}
