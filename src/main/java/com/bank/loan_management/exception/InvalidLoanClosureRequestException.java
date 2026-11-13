package com.bank.loan_management.exception;

public class InvalidLoanClosureRequestException extends RuntimeException{
    public InvalidLoanClosureRequestException(String message){
        super(message);
    }
}
