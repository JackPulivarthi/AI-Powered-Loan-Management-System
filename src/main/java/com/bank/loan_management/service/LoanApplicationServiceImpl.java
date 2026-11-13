package com.bank.loan_management.service;

import com.bank.loan_management.dto.ClosedLoanResponse;
import com.bank.loan_management.dto.LoanApplicationRequest;
import com.bank.loan_management.dto.LoanApplicationResponse;
import com.bank.loan_management.dto.StatusUpdate;
import com.bank.loan_management.entity.Customer;
import com.bank.loan_management.entity.LoanApplication;
import com.bank.loan_management.entity.LoanRepayment;
import com.bank.loan_management.enums.LoanStatus;
import com.bank.loan_management.enums.RepaymentStatus;
import com.bank.loan_management.exception.InvalidLoanClosureRequestException;
import com.bank.loan_management.exception.InvalidLoanStatusException;
import com.bank.loan_management.mapper.Mapper;
import com.bank.loan_management.repository.CustomerRepository;
import com.bank.loan_management.repository.LoanApplicationRepository;
import com.bank.loan_management.repository.LoanRepaymentRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class LoanApplicationServiceImpl implements LoanApplicationService{

    private final LoanApplicationRepository loanApplicationRepository;
    private final Mapper mapper;
    private final CustomerRepository customerRepository;
    private final LoanRepaymentRepository loanRepaymentRepository;


    public LoanApplicationServiceImpl(LoanApplicationRepository loanApplicationRepository, Mapper mapper, CustomerRepository customerRepository, LoanRepaymentRepository loanRepaymentRepository){
        this.loanApplicationRepository=loanApplicationRepository;
        this.mapper = mapper;
        this.customerRepository = customerRepository;
        this.loanRepaymentRepository = loanRepaymentRepository;
    }

    @Override
    public LoanApplicationResponse loanApplication(LoanApplicationRequest loanApplicationRequest) {

        LoanApplication loanApplication = new LoanApplication();

        Customer customer = customerRepository.findById(loanApplicationRequest.getCustomerId()).orElseThrow(()-> new EntityNotFoundException("Customer not found"));
        loanApplication.setCustomer(customer);

        loanApplication.setLoanAmount(loanApplicationRequest.getLoanAmount());
        loanApplication.setLoanTenure(loanApplicationRequest.getLoanTenure());
        loanApplication.setMonthlyIncome(loanApplicationRequest.getMonthlyIncome());

        long emi = (long) calculateEmi(loanApplicationRequest.getLoanAmount(),loanApplicationRequest.getLoanTenure(),12.0);
        loanApplication.setEmi(emi);

        loanApplication.setStatus(LoanStatus.PENDING);

        return mapper.mapLoanEntitytoDto(loanApplicationRepository.save(loanApplication));
    }

    @Override
    public LoanApplicationResponse findApplicationById(Integer applicationId) {
        LoanApplication loanApplication = loanApplicationRepository.findById(applicationId).orElseThrow(()-> new EntityNotFoundException("Application not found"));
        return mapper.mapLoanEntitytoDto(loanApplication);
    }

    @Override
    public List<LoanApplicationResponse> getAllLoanApplications() {
        List<LoanApplication> loanApplications= loanApplicationRepository.findAll();
        return mapper.mapLoanEntitiestoDTO(loanApplications);
    }

    @Transactional
    @Override
    public LoanApplicationResponse statusUpdate(StatusUpdate statusUpdate, Integer applicationId) {

        LoanApplication loanApplication = loanApplicationRepository.findById(applicationId).orElseThrow(()-> new EntityNotFoundException("Application not found"));

        if(loanApplication.getStatus()!=LoanStatus.PENDING)
            throw  new InvalidLoanStatusException("Loan already finalised");
        loanApplication.setStatus(LoanStatus.valueOf(statusUpdate.getStatus()));

        if(loanApplication.getStatus()==LoanStatus.APPROVED)
            generateRepaymentSchedule(loanApplication);

        LoanApplication updatedLoanApplication =loanApplicationRepository.save(loanApplication);

        return mapper.mapLoanEntitytoDto(updatedLoanApplication);
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

    public void generateRepaymentSchedule(LoanApplication loanApplication){
        int tenure = loanApplication.getLoanTenure();
        long emiAmount = loanApplication.getEmi();
        LocalDate approvalDate = LocalDate.now();

        List<LoanRepayment> loanRepayments= new ArrayList<>();

        for(int i=1; i<= tenure; i++){
            LoanRepayment loanRepayment = new LoanRepayment();
            loanRepayment.setInstallmentNumber(i);
            loanRepayment.setDueDate(approvalDate.plusMonths(i));
            loanRepayment.setEmiAmount(emiAmount);
            loanRepayment.setPaymentStatus(RepaymentStatus.PENDING);
            loanRepayment.setPaidDate(null);
            loanRepayment.setLoanApplication(loanApplication);
            loanRepayments.add(loanRepayment);
        }
        loanRepaymentRepository.saveAll(loanRepayments);


    }
    @Transactional
    @Override
    public ClosedLoanResponse closedLoan(Integer applicationId) {
        boolean close =closeLoanIfAllInstallmentsPaid(applicationId);
        if(close) {
            ClosedLoanResponse closedLoanResponse = new ClosedLoanResponse();
            closedLoanResponse.setApplicationId(applicationId);
            closedLoanResponse.setLoanStatus(LoanStatus.CLOSED);
            closedLoanResponse.setMessage("Loan closed successfully");
            return closedLoanResponse;
        }else {
            throw new InvalidLoanClosureRequestException("Invalid closure loan request");
        }
    }

    @Override
    public boolean closeLoanIfAllInstallmentsPaid(Integer applicationId) {
        LoanApplication loanApplication =loanApplicationRepository.findById(applicationId).orElseThrow(()-> new EntityNotFoundException("Application not found"));
        List<LoanRepayment> loanRepayments = loanRepaymentRepository.findByLoanApplication_ApplicationIdOrderByInstallmentNumberAsc(applicationId);
        if(!loanRepayments.isEmpty()) {
            boolean closed = loanRepayments.stream()
                    .allMatch(loanRepayment -> loanRepayment.getPaymentStatus() == RepaymentStatus.PAID);

            if (closed) {
                if(loanApplication.getStatus()==LoanStatus.APPROVED) {
                    loanApplication.setStatus(LoanStatus.CLOSED);
                    loanApplicationRepository.save(loanApplication);
                    return true;
                }
            } else
                return false;
        }
        else
            return false;
        return false;
    }

}
