package com.bank.loan_management.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TotalAmountResponse {
    private Integer applicationId;
    private Long loanAmount;
    private Long totalAmountPaid;
    private Long totalAmountOutstanding;
}
