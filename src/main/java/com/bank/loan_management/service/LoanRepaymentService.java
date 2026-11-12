package com.bank.loan_management.service;

import com.bank.loan_management.dto.LoanRepaymentResponse;
import com.bank.loan_management.dto.RepaymentScheduleResponse;

public interface LoanRepaymentService {

    RepaymentScheduleResponse loanRepaymentsSchedule(Integer applicationId);
    LoanRepaymentResponse markRepaymentAsPaid(Integer repaymentId);
    LoanRepaymentResponse getRepaymentById(Integer applicationId, Integer repaymentId);
}
