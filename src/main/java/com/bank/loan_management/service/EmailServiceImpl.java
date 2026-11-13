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

        message.setFrom("pulivarthijackmain@gmail.com");
        try {
            javaMailSender.send(message);
        }catch (MailException exception){
            log.error("Failed to send application confirmation email", exception);
        }
    }

    @Override
    public void sendLoanApprovalEmail(String to, Integer applicationId, long loanAmount) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(to);
        message.setSubject("Your Loan Application Has Been Approved");

        message.setText(
                "Dear Customer,\n\n" +
                        "Congratulations! Your loan application has been approved successfully.\n\n" +
                        "Application ID: " + applicationId + "\n" +
                        "Approved Loan Amount: ₹" + loanAmount + "\n\n" +
                        "Thank you for choosing our bank."
        );

        try {
            javaMailSender.send(message);
        } catch (MailException exception) {
            log.error("Failed to send loan approval email for applicationId {}", applicationId, exception);
        }
    }
}
