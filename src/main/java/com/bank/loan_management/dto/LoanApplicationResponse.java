package com.bank.loan_management.dto;

import lombok.Data;

@Data
public class LoanApplicationResponse {

//    {
//        "applicationId": 101,
//            "customerId": 1,
//            "loanAmount": 500000,
//            "loanTenure": 36,
//            "monthlyIncome": 50000,
//            "emi": 16607.15,
//            "status": "PENDING"
//    }

    private Integer applicationId;
    private Integer customerId;
    private Long loanAmount;
    private Integer loanTenure;
    private Long monthlyIncome;
    private double emi;
    private String status;
}
