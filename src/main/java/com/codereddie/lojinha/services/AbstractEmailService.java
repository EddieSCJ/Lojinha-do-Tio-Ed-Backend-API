package com.codereddie.lojinha.services;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.codereddie.lojinha.domain.Client;
import com.codereddie.lojinha.domain.Orderr;

public abstract class AbstractEmailService implements EmailService {

	@Value("${default.sender}")
	private String sender;
	
	@Autowired
	private TemplateEngine templateEngine;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Override
	public void sendOrderConfirmationEmail(Orderr order) {
		SimpleMailMessage sm = prepareSimpleMailMessageFromOrder(order);
		sendEmail(sm);
	}

	protected SimpleMailMessage prepareSimpleMailMessageFromOrder(Orderr order) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(order.getClient().getEmail());
		sm.setFrom(sender);
		sm.setSubject("Order confirmed, cod: "+order.getId());
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText(order.toString());
		return sm;
	}

	protected String htmlFromOrder( Orderr order) {
		Context context = new Context();
		context.setVariable("order", order);
		context.setVariable("address", order.getAddress().toString());
		return templateEngine.process("email/orderConfirm", context);
	}
	
	protected MimeMessage preapreMimeMessageFromOrder(Orderr order) throws MessagingException {
		MimeMessage mm = javaMailSender.createMimeMessage();
		MimeMessageHelper mmh = new MimeMessageHelper(mm, true);
		
		mmh.setTo(order.getClient().getEmail());
		mmh.setFrom(sender);
		mmh.setSubject("Order confirmed");
		mmh.setSentDate(new Date(System.currentTimeMillis()));
		mmh.setText(htmlFromOrder(order), true);
		
		return mm;
	}
	
	@Override
	public void sendNewPasswordEmail(Client client, String newPass) {
		SimpleMailMessage sm = prepareSimpleMailMessageFromClient(client, newPass);
		sendEmail(sm);
	}
	
	protected SimpleMailMessage prepareSimpleMailMessageFromClient(Client client, String newPass) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(client.getEmail());
		sm.setFrom(sender);
		sm.setSubject("New Password");
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText("New Password: "+newPass);
		return sm;
	}
	
	@Override
	public void sendOrderConfirmationHTMLEmail(Orderr order) {
		MimeMessage mm;
		try {
			mm = preapreMimeMessageFromOrder(order);
			sendHTMLEmail(mm);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			sendOrderConfirmationEmail(order);
		}
	}



}
