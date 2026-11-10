package com.bank.loan_management.dto;

import lombok.Data;

@Data
public class LoanApplicationRequest {

//    {
//        "customerId": 1,
//            "loanAmount": 500000,
//            "loanTenure": 36,
//            "monthlyIncome": 50000
//    }

    private Integer customerId;
    private Long loanAmount;
    private Integer loanTenure;
    private Long monthlyIncome;

}
