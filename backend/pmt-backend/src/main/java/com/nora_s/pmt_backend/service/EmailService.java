package com.nora_s.pmt_backend.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        message.setFrom("pmt.notifications@gmail.com");

        mailSender.send(message);
    }

    void sendTaskAssignedEmail(String email, String title, String username) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}