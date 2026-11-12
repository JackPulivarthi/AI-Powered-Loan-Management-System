package com.bank.loan_management.service;

import com.bank.loan_management.dto.LoanRepaymentResponse;
import com.bank.loan_management.dto.RepaymentScheduleResponse;
import com.bank.loan_management.entity.LoanRepayment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepaymentService {

    RepaymentScheduleResponse loanRepaymentsSchedule(Integer applicationId);
    LoanRepaymentResponse markRepaymentAsPaid(Integer repaymentId);
    LoanRepaymentResponse getRepaymentById(Integer applicationId, Integer repaymentId);
}
