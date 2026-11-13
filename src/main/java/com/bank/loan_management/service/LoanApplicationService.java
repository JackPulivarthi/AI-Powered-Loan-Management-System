package com.bank.loan_management.service;

import com.bank.loan_management.dto.ClosedLoanResponse;
import com.bank.loan_management.dto.LoanApplicationRequest;
import com.bank.loan_management.dto.LoanApplicationResponse;
import com.bank.loan_management.dto.StatusUpdate;
import com.bank.loan_management.entity.LoanApplication;
import com.bank.loan_management.enums.LoanStatus;

import java.util.List;

public interface LoanApplicationService {
    LoanApplicationResponse loanApplication(LoanApplicationRequest loanApplicationRequest);
    LoanApplicationResponse findApplicationById(Integer applicationId);
    List<LoanApplicationResponse> getAllLoanApplications();
    LoanApplicationResponse statusUpdate(StatusUpdate statusUpdate, Integer applicationId);
    ClosedLoanResponse closedLoan(Integer applicationId);
    boolean closeLoanIfAllInstallmentsPaid(Integer applicationId);
}
