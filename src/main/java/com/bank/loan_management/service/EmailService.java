package com.bank.loan_management.service;

public interface EmailService {

    void sendApplicationConfirmationEmail(String to, Integer applicationId);

}
