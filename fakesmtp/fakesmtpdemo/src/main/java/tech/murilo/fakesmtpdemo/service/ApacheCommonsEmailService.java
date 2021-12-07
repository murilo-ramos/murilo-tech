package tech.murilo.fakesmtpdemo.service;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.stereotype.Service;

@Service
public class ApacheCommonsEmailService implements EmailService {
	
	private static final String HOST = "localhost";
	private static final int PORT = 25;
	
	@Override
	public void send(EmailData emailData) {
		var email = new SimpleEmail();
		email.setHostName(HOST);
		email.setSmtpPort(PORT);
		
		try {
			email.setFrom(emailData.getFrom());
			email.setSubject(emailData.getSubject());
			email.setMsg(emailData.getMessage());
			
			email.addTo(emailData.getTo());
			
			email.send();
		} catch (EmailException ex) {
			throw new EmailServiceException(ex.getMessage());
		}
	}
}
