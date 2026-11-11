package com.bank.loan_management.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class LoanApplicationRequest {

//    {
//        "customerId": 1,
//            "loanAmount": 500000,
//            "loanTenure": 36,
//            "monthlyIncome": 50000
//    }

    @NotNull
    @Positive
    private Integer customerId;

    @NotNull
    @Positive
    private Long loanAmount;

    @NotNull
    @Min(1)
    private Integer loanTenure;

    @NotNull
    @Positive
    private Long monthlyIncome;

}
