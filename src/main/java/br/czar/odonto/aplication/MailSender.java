package br.czar.odonto.aplication;


import java.io.InputStream;
import java.util.Properties;

import jakarta.mail.*;
import jakarta.mail.internet.*;

public class MailSender {
	private String user;
	private String pass;

	public MailSender() {
		Properties prop = new Properties();
		try (InputStream is = this.getClass().getResourceAsStream("/config/email.properties")) {
			prop.load(is);
		} catch (Exception e) {
			System.out.println("\n\n\nDEVE CRIAR O ARQUIVO (email.properties) EM (src/config)\n\n\n");
		}

		setUser(prop.getProperty("user"));
		setPass(prop.getProperty("pass"));
	}

	public void sendMail(String message, String destination, String subject) throws MessagingException {
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");

		Session session = Session.getInstance(props,
			new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(getUser(), getPass());
				}
		});

		try {
			Message mail = new MimeMessage(session);
			mail.setFrom(new InternetAddress(getUser()));
			mail.setSubject(subject);
			mail.setText(message);
			mail.setRecipients(
				Message.RecipientType.TO,
				InternetAddress.parse(destination)
			);;

			Transport.send(mail);
		} catch (MessagingException e) {
			System.out.println("\n\n\nHouve um erro ao enviar a mensagem.\n\n\n");
			e.printStackTrace();
			throw e;
		}
	}

	private String getUser() {
		return this.user;
	}

	private void setUser(String user) {
		this.user = user;
	}

	private String getPass() {
		return this.pass;
	}

	private void setPass(String pass) {
		this.pass = pass;
	}
}
