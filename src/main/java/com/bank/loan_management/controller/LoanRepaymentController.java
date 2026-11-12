package com.bank.loan_management.controller;

import com.bank.loan_management.dto.RepaymentScheduleResponse;
import com.bank.loan_management.service.LoanRepaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/repayment")
public class LoanRepaymentController {
    LoanRepaymentService loanRepaymentService;

    LoanRepaymentController(LoanRepaymentService loanRepaymentService){
        this.loanRepaymentService = loanRepaymentService;
    }

    @GetMapping("{applicationId}/repayments")
    public ResponseEntity<RepaymentScheduleResponse> loanRepaymentsSchedule(@PathVariable("applicationId") Integer applicationId){
        RepaymentScheduleResponse repaymentScheduleResponse = loanRepaymentService.loanRepaymentsSchedule(applicationId);
        return ResponseEntity.ok(repaymentScheduleResponse);
    }
}
