package com.bank.loan_management.service;

public interface EmailService {

    void sendApplicationConfirmationEmail(String to, Integer applicationId);
    void sendLoanApprovalEmail(String to, Integer applicationId, long loanAmount);

}
