package com.bank.loan_management.dto;

import com.bank.loan_management.entity.LoanApplication;
import com.bank.loan_management.enums.RepaymentStatus;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoanRepaymentResponse {

    private Integer repaymentId;
    private Integer installmentNumber;
    private LocalDate dueDate;
    private Long emiAmount;
    private RepaymentStatus paymentStatus;
    private LocalDate paidDate;
    private LoanApplication loanApplication;
}
