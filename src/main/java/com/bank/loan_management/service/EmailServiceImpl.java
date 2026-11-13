package com.bank.loan_management.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmailServiceImpl implements EmailService{

    private final JavaMailSender javaMailSender;


    public EmailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendApplicationConfirmationEmail(
            String to,
            Integer applicationId) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(to);
        message.setSubject("Loan Application Received");

        message.setText(
                "Dear Customer,\n\n" +
                        "Your loan application has been successfully submitted.\n" +
                        "Application ID: " + applicationId + "\n\n" +
                        "Thank you for choosing our bank."
        );

        message.setFrom("random@gmail.com");
        try {
            javaMailSender.send(message);
        }catch (MailException exception){
            log.error("Failed to send application confirmation email", exception);
        }
    }
}
