package com.bank.loan_management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RepaymentHistoryResponse {
    private Integer applicationId;
    private List<LoanRepaymentResponse> loanRepaymentResponses;
}
