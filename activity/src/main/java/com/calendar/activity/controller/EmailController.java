package com.calendar.activity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;



@RestController
public class EmailController {
	@Autowired
    private JavaMailSender emailSender;
	 @CrossOrigin(origins = "http://localhost:4200/page2")
    @PostMapping("/sendJsonEmail")
    public ResponseEntity<String> sendJsonEmail(@RequestBody String jsonContent) {
        try {
            String to = "pavsz0212@gmail.com";
            String subject = "JSON Data Email";

            sendEmail(to, subject, jsonContent);

            return ResponseEntity.ok("Email sent successfully!");
        } catch (MessagingException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Email could not be sent.");
        }
    }

    private void sendEmail(String to, String subject, String jsonContent) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(to);
        helper.setSubject(subject);

        
        helper.setText(jsonContent, true);

        emailSender.send(message);
    }
}