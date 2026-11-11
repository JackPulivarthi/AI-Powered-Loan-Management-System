package com.bank.loan_management.service;

import com.bank.loan_management.dto.LoanApplicationRequest;
import com.bank.loan_management.dto.LoanApplicationResponse;

import java.util.List;

public interface LoanApplicationService {
    LoanApplicationResponse loanApplication(LoanApplicationRequest loanApplicationRequest);
    LoanApplicationResponse findApplicationById(Integer applicationId);
    List<LoanApplicationResponse> getAllLoanApplications();
}
