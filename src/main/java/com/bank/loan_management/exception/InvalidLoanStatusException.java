package com.bank.loan_management.exception;

public class InvalidLoanStatusException  extends RuntimeException{
    public InvalidLoanStatusException(String message){
        super(message);
    }
}
