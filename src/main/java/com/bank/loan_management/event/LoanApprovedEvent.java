package com.bank.loan_management.event;

public class LoanApprovedEvent {
    private final Integer applicationId;
    private final String customerEmail;
    private final long loanAmount;

    public LoanApprovedEvent(
            Integer applicationId,
            String customerEmail,
            long loanAmount) {

        this.applicationId = applicationId;
        this.customerEmail = customerEmail;
        this.loanAmount = loanAmount;
    }

    public Integer getApplicationId() {
        return applicationId;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public long getLoanAmount() {
        return loanAmount;
    }
}
