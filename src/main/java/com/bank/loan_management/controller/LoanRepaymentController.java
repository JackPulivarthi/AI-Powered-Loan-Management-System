package com.bank.loan_management.controller;

import com.bank.loan_management.dto.LoanRepaymentResponse;
import com.bank.loan_management.dto.RepaymentScheduleResponse;
import com.bank.loan_management.service.LoanRepaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PatchMapping("/{repaymentId}/pay")
    public ResponseEntity<LoanRepaymentResponse> markRepaymentAsPaid(
            @PathVariable Integer repaymentId) {

        return ResponseEntity.ok(
                loanRepaymentService.markRepaymentAsPaid(repaymentId)
        );
    }

    @GetMapping("/{applicationId}/repayments/{repaymentId}")
    public ResponseEntity<LoanRepaymentResponse> getRepaymentbyId(@PathVariable("applicationId") Integer applicationId, @PathVariable("repaymentId") Integer repaymentId){
        LoanRepaymentResponse loanRepaymentResponse = loanRepaymentService.getRepaymentById(applicationId, repaymentId);
        return ResponseEntity.ok(loanRepaymentResponse);
    }
}
