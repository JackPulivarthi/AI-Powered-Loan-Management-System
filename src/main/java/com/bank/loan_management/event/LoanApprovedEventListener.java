package com.bank.loan_management.event;

import com.bank.loan_management.service.EmailService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

public class LoanApprovedEventListener {
    private final EmailService emailService;

    public LoanApprovedEventListener(EmailService emailService) {
        this.emailService = emailService;
    }

    @Async("loanNotificationExecutor")
    public void handleLoanApprovedEvent(LoanApprovedEvent event) {

        emailService.sendLoanApprovalEmail(
                event.getCustomerEmail(),
                event.getApplicationId(),
                event.getLoanAmount()
        );
    }
}
