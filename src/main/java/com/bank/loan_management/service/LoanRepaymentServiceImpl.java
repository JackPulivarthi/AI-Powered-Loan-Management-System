package com.bank.loan_management.service;

import com.bank.loan_management.dto.LoanApplicationResponse;
import com.bank.loan_management.dto.LoanRepaymentResponse;
import com.bank.loan_management.dto.RepaymentScheduleResponse;
import com.bank.loan_management.entity.LoanApplication;
import com.bank.loan_management.entity.LoanRepayment;
import com.bank.loan_management.enums.LoanStatus;
import com.bank.loan_management.enums.RepaymentStatus;
import com.bank.loan_management.repository.LoanApplicationRepository;
import com.bank.loan_management.repository.LoanRepaymentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class LoanRepaymentServiceImpl implements LoanRepaymentService{

    private final LoanApplicationRepository loanApplicationRepository;
    private final LoanRepaymentRepository loanRepaymentRepository;

    public LoanRepaymentServiceImpl(LoanApplicationRepository loanApplicationRepository, LoanRepaymentRepository loanRepaymentRepository){
        this.loanApplicationRepository = loanApplicationRepository;
        this.loanRepaymentRepository = loanRepaymentRepository;
    }

    @Override
    public RepaymentScheduleResponse loanRepaymentsSchedule(Integer applicationId) {
        LoanApplication loanApplication = loanApplicationRepository.findById(applicationId).orElseThrow(()-> new EntityNotFoundException("Application not found"));
        if(!(loanApplication.getStatus()== LoanStatus.APPROVED))
            throw new EntityNotFoundException("Repayment schedule not found");

        List<LoanRepayment> loanRepayments=loanRepaymentRepository.findByLoanApplication_ApplicationIdOrderByInstallmentNumberAsc(applicationId);
        RepaymentScheduleResponse repaymentScheduleResponse= new RepaymentScheduleResponse();
        repaymentScheduleResponse.setApplicationId(loanApplication.getApplicationId());
        repaymentScheduleResponse.setLoanAmount(loanApplication.getLoanAmount());
        repaymentScheduleResponse.setLoanTenure(loanApplication.getLoanTenure());
        repaymentScheduleResponse.setEmiAmount(loanApplication.getEmi());
        repaymentScheduleResponse.setTotalInstallments(loanRepayments.size());
        int paidInstallments = (int) loanRepayments.stream().filter(loanRepayment -> loanRepayment.getPaymentStatus()==RepaymentStatus.PAID).count();
        int pendingInstallments = loanRepayments.stream().filter(loanRepayment -> loanRepayment.getPaymentStatus()==RepaymentStatus.PENDING).count();
        repaymentScheduleResponse.setPaidInstallments(paidInstallments);
        repaymentScheduleResponse.setPendingInstallments(pendingInstallments);
        repaymentScheduleResponse.setRepayments(loanRepayments);

        return repaymentScheduleResponse;



    }
}
