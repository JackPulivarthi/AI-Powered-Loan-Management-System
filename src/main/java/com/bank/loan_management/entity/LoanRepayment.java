package com.bank.loan_management.entity;

import com.bank.loan_management.enums.RepaymentStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class LoanRepayment {

    @Id
    @GeneratedValue
    private Integer repaymentId;
    private Integer installmentNumber;
    private LocalDate dueDate;
    private long emiAmount;

    @Enumerated(EnumType.STRING)
    private RepaymentStatus paymentStatus;
    private LocalDate paidDate;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "application_Id")
    private LoanApplication loanApplication;
}
