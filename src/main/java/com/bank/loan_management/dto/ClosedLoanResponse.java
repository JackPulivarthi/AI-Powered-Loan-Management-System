package com.bank.loan_management.dto;

import com.bank.loan_management.enums.LoanStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClosedLoanResponse {
    private Integer applicationId;
    private LoanStatus loanStatus;
    private String message;
}
