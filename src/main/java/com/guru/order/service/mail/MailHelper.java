package com.guru.order.service.mail;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class MailHelper {
	
	private String from = "guruorderapp@gmail.com";
	
	@Autowired
	private JavaMailSenderImpl mailSender;
	
	public void sendMail(String[] attachments, String orderTime) throws Exception {
		MimeMessage message = mailSender.createMimeMessage();
		
		MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
		messageHelper.setFrom(from);
		messageHelper.setTo("pavanpinnu@gmail.com");
		messageHelper.setText("Test Body", true);
		messageHelper.setSubject("Order-" + orderTime);
		
		
		for (String filePath : attachments) {
			FileSystemResource file = new FileSystemResource(filePath);
			messageHelper.addAttachment(file.getFilename(), file);
		}
		
		mailSender.send(message);
	}

}
