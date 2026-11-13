package com.bank.loan_management.service;

import com.bank.loan_management.dto.LoanRepaymentResponse;
import com.bank.loan_management.dto.RepaymentScheduleResponse;
import com.bank.loan_management.dto.RepaymentTotalPaidResponse;
import com.bank.loan_management.dto.TotalAmountResponse;

import java.util.List;

public interface LoanRepaymentService {

    RepaymentScheduleResponse loanRepaymentsSchedule(Integer applicationId);
    LoanRepaymentResponse markRepaymentAsPaid(Integer repaymentId);
    LoanRepaymentResponse getRepaymentById(Integer applicationId, Integer repaymentId);
    List<LoanRepaymentResponse> overdue();
    RepaymentTotalPaidResponse totalPaidAmount(Integer applicationId);
    TotalAmountResponse totalAmountOutstanding(Integer applicationId);
}
