package com.bank.loan_management.dto;

import com.bank.loan_management.entity.LoanRepayment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RepaymentScheduleResponse {

    private Integer applicationId;

    private Long loanAmount;

    private Integer loanTenure;

    private Long emiAmount;

    private Integer totalInstallments;

    private Integer paidInstallments;

    private Integer pendingInstallments;

    private List<LoanRepaymentResponse> repayments;
}
