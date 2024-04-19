package com.store.greenShoes.service;

import java.util.Properties;

import org.springframework.stereotype.Service;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;

import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

@Service
public class MailService {
	private final String fromAddress = "greenshoeswebstore@gmail.com";
	private final String username = "greenshoeswebstore@gmail.com";
	private final String password = "regszyzwfeswzbkb";
	private final String host = "smtp.gmail.com";

	private static Properties props;
	private static Authenticator accountAuthenticator;

	public static MailService mailServiceObj;

	public static MailService GetMailServiceObject() {
		if (mailServiceObj == null)
			mailServiceObj = new MailService();
		return mailServiceObj;
	}

	private MailService() {
		props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "587");
		accountAuthenticator = new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		};
	}

	public boolean sendMail(String toAddress, String emailSubject, String emailBody) {
		try {
			Session session = Session.getInstance(props, accountAuthenticator);
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(fromAddress));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toAddress));
			message.setSubject(emailSubject);
			message.setContent(emailBody, "text/html");
			Transport.send(message);

			return true;

		} catch (Exception ex) {
			return false;
		}

	}
}
