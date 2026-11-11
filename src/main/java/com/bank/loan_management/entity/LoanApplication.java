package com.bank.loan_management.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoanApplication {


//        "applicationId": 101,
//            "customerId": 1,
//            "loanAmount": 500000,
//            "loanTenure": 36,
//            "monthlyIncome": 50000,
//            "emi": 16607.15,
//            "status": "PENDING"

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "application_Id")
    private Integer applicationId;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "loanAmount")
    private Long loanAmount;

    @Column(name = "loan_tenure")
    private Integer loanTenure;

    @Column(name = "monthly_income")
    private Long monthlyIncome;

    @Column(name = "emi")
    private double emi;

    @Column(name = "status")
    private String status;




}
