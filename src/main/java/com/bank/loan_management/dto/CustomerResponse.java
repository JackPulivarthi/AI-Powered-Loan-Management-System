package com.bank.loan_management.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CustomerResponse {
    private Integer customerId;
    private String customerFullName;
    private String customerEmailAddress;
    private String customerMobileNumber;
    private LocalDate customerDateOfBirth;
}
