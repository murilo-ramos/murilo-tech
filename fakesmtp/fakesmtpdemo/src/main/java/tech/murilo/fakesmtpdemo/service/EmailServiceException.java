package tech.murilo.fakesmtpdemo.service;

public class EmailServiceException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EmailServiceException(String message) {
		super(message);
	}

}
