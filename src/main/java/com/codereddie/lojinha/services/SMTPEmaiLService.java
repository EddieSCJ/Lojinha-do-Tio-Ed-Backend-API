package com.codereddie.lojinha.services;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class SMTPEmaiLService extends AbstractEmailService {

	@Autowired
	private MailSender mailSender;
	
	@Autowired
	private JavaMailSender jvs;
	
	@Override
	public void sendEmail(SimpleMailMessage message) {
		// TODO Auto-generated method stub
		mailSender.send(message);
	}

	@Override
	public void sendHTMLEmail(MimeMessage message) {
		// TODO Auto-generated method stub
		jvs.send(message);
	}

}
