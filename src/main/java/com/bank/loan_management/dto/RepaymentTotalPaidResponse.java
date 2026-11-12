package com.bank.loan_management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RepaymentTotalPaidResponse {
    Integer applicationId;
    long totalAmountPaid;
}
