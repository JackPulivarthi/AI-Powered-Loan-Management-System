package com.bank.loan_management.service;

import com.bank.loan_management.dto.LoanApplicationRequest;
import com.bank.loan_management.dto.LoanApplicationResponse;

public interface LoanApplicationService {
    LoanApplicationResponse loanApplication(LoanApplicationRequest loanApplicationRequest);
    LoanApplicationResponse findApplicationById(Integer applicationId);
}
