package tech.murilo.fakesmtpdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;


import tech.murilo.fakesmtpdemo.service.EmailData;
import tech.murilo.fakesmtpdemo.service.EmailService;


@Controller
public class EmailController {
	
	private EmailService emailService;
	
	@Autowired
	public void setEmailService(EmailService emailService) {
		this.emailService = emailService;
	} 
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@PostMapping("/")
	@ResponseBody
	public String sendEmail(@RequestBody EmailData emailData) {
		emailService.send(emailData);
		return "";
	}
}
