package com.bank.loan_management.service;

import com.bank.loan_management.dto.LoanRepaymentResponse;
import com.bank.loan_management.dto.RepaymentScheduleResponse;
import com.bank.loan_management.dto.RepaymentTotalPaidResponse;
import com.bank.loan_management.dto.TotalAmountResponse;
import com.bank.loan_management.entity.LoanApplication;
import com.bank.loan_management.entity.LoanRepayment;
import com.bank.loan_management.enums.LoanStatus;
import com.bank.loan_management.enums.RepaymentStatus;
import com.bank.loan_management.mapper.Mapper;
import com.bank.loan_management.repository.LoanApplicationRepository;
import com.bank.loan_management.repository.LoanRepaymentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class LoanRepaymentServiceImpl implements LoanRepaymentService{

    private final LoanApplicationRepository loanApplicationRepository;
    private final LoanRepaymentRepository loanRepaymentRepository;
    private final Mapper mapper;

    public LoanRepaymentServiceImpl(LoanApplicationRepository loanApplicationRepository, LoanRepaymentRepository loanRepaymentRepository, Mapper mapper){
        this.loanApplicationRepository = loanApplicationRepository;
        this.loanRepaymentRepository = loanRepaymentRepository;
        this.mapper = mapper;
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
        int pendingInstallments= (int) loanRepayments.stream().filter(loanRepayment -> loanRepayment.getPaymentStatus()==RepaymentStatus.PENDING).count();
        repaymentScheduleResponse.setPaidInstallments(paidInstallments);
        repaymentScheduleResponse.setPendingInstallments(pendingInstallments);

        repaymentScheduleResponse.setRepayments(mapper.mapLoanRepaymentEntitytoDTO(loanRepayments));

        return repaymentScheduleResponse;



    }

    @Override
    public LoanRepaymentResponse markRepaymentAsPaid(Integer repaymentId) {

        LoanRepayment loanRepayment = loanRepaymentRepository.findById(repaymentId)
                .orElseThrow(() -> new EntityNotFoundException("Repayment not found"));
        if (loanRepayment.getPaymentStatus() == RepaymentStatus.PAID) {
            throw new IllegalStateException("This installment is already paid");
        }

        loanRepayment.setPaymentStatus(RepaymentStatus.PAID);
        loanRepayment.setPaidDate(LocalDate.now());

        LoanRepayment savedRepayment = loanRepaymentRepository.save(loanRepayment);

        return mapper.mapLoanRepaymentEntitytoDTO(savedRepayment);
    }

    @Override
    public LoanRepaymentResponse getRepaymentById(Integer applicationId, Integer repaymentId) {
        loanApplicationRepository.findById(applicationId).orElseThrow(()-> new EntityNotFoundException("Application id doesnot exist"));
        LoanRepayment loanRepayment = loanRepaymentRepository.findByLoanApplication_ApplicationIdAndRepaymentId(applicationId, repaymentId);
        if (loanRepayment == null) {
            throw new EntityNotFoundException("Repayment not found");
        }
        return mapper.mapLoanRepaymentEntitytoDTO(loanRepayment);
    }

    @Override
    public List<LoanRepaymentResponse> overdue() {
        List<LoanRepayment> loanRepayments = loanRepaymentRepository.findByDueDateBeforeAndPaymentStatus(LocalDate.now(), RepaymentStatus.PENDING);
        List<LoanRepaymentResponse> loanRepaymentResponses=mapper.mapLoanRepaymentEntitytoDTO(loanRepayments);
        return loanRepaymentResponses;
    }

    @Override
    public RepaymentTotalPaidResponse totalPaidAmount(Integer applicationId) {
        loanApplicationRepository.findById(applicationId).orElseThrow(()->new EntityNotFoundException("application not found"));
        List<LoanRepayment> loanRepayments = loanRepaymentRepository.findByLoanApplication_ApplicationIdAndPaymentStatus(applicationId, RepaymentStatus.PAID);
        long total = 0;
        for (LoanRepayment loanRepayment : loanRepayments) {
            total += loanRepayment.getEmiAmount();
        }
        RepaymentTotalPaidResponse repaymentTotalPaidResponse = new RepaymentTotalPaidResponse();
        repaymentTotalPaidResponse.setApplicationId(applicationId);
        repaymentTotalPaidResponse.setTotalAmountPaid(total);

        return repaymentTotalPaidResponse;
    }

    @Override
    public TotalAmountResponse totalAmountOutstanding(Integer applicationId) {
        LoanApplication loanApplication = loanApplicationRepository.findById(applicationId).orElseThrow(()-> new EntityNotFoundException("Application not found"));
        RepaymentTotalPaidResponse repaymentTotalPaidResponse = totalPaidAmount(applicationId);
        Long outstandingAmount = loanApplication.getLoanAmount() - repaymentTotalPaidResponse.getTotalAmountPaid();
        TotalAmountResponse totalAmountResponse = new TotalAmountResponse();
        totalAmountResponse.setApplicationId(loanApplication.getApplicationId());
        totalAmountResponse.setLoanAmount(loanApplication.getLoanAmount());
        totalAmountResponse.setTotalAmountOutstanding(outstandingAmount);
        totalAmountResponse.setTotalAmountPaid(repaymentTotalPaidResponse.getTotalAmountPaid());
        return totalAmountResponse;
    }


}
