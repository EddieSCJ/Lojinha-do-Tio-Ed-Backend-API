package com.codereddie.lojinha.services;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

public class MockEmailService extends AbstractEmailService {

	private static final Logger LOG = LoggerFactory.getLogger(MockEmailService.class);
	
	@Override
	public void sendEmail(SimpleMailMessage message) {
		LOG.info("Simulate email send");
		LOG.info(message.toString());
		LOG.info("Email sent");
	}

	@Override
	public void sendHTMLEmail(MimeMessage message) {
		// TODO Auto-generated method stub
		
	}

}
