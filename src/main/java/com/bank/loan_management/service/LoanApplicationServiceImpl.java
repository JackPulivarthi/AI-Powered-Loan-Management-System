package com.bank.loan_management.service;

import com.bank.loan_management.dto.LoanApplicationRequest;
import com.bank.loan_management.dto.LoanApplicationResponse;
import com.bank.loan_management.entity.LoanApplication;
import com.bank.loan_management.mapper.Mapper;
import com.bank.loan_management.repository.LoanApplicationRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class LoanApplicationServiceImpl implements LoanApplicationService{

    private final LoanApplicationRepository loanApplicationRepository;
    private final Mapper mapper;


    LoanApplicationServiceImpl(LoanApplicationRepository loanApplicationRepository, LoanApplication loanApplication, Mapper mapper){
        this.loanApplicationRepository=loanApplicationRepository;
        this.mapper = mapper;
    }

    @Override
    public LoanApplicationResponse loanApplication(LoanApplicationRequest loanApplicationRequest) {

        LoanApplication loanApplication = new LoanApplication();
        loanApplication.getCustomer().setCustomerId(loanApplicationRequest.getCustomerId());
        loanApplication.setLoanAmount(loanApplicationRequest.getLoanAmount());
        loanApplication.setLoanTenure(loanApplicationRequest.getLoanTenure());
        loanApplication.setMonthlyIncome(loanApplicationRequest.getMonthlyIncome());

        double emi =calculateEmi(loanApplicationRequest.getLoanAmount(),loanApplicationRequest.getLoanTenure(),12.0);
        loanApplication.setEmi(emi);

        loanApplication.setStatus("Pending");

        return mapper.mapLoanEntitytoDto(loanApplicationRepository.save(loanApplication));
    }


    public static double calculateEmi(double loanAmount, int loanTenureMonths, double annualInterestRate) {
        // 1. Convert annual interest rate to a monthly decimal value
        double monthlyRate = annualInterestRate / (12 * 100);

        // 2. Apply the EMI mathematical formula
        double emi = (loanAmount * monthlyRate * Math.pow(1 + monthlyRate, loanTenureMonths))
                / (Math.pow(1 + monthlyRate, loanTenureMonths) - 1);

        // 3. Round up to 2 decimal places cleanly
        return BigDecimal.valueOf(emi)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
    }
}
