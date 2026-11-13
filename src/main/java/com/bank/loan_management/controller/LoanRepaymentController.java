package com.bank.loan_management.controller;

import com.bank.loan_management.dto.*;
import com.bank.loan_management.service.LoanRepaymentService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.List;

@RestController
@RequestMapping("api/repayment")
public class LoanRepaymentController {
    private final LoanRepaymentService loanRepaymentService;

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

    @GetMapping("/overdue")
    public ResponseEntity<List<LoanRepaymentResponse>> overdue(){
        List<LoanRepaymentResponse> loanRepaymentResponses = loanRepaymentService.overdue();
        return ResponseEntity.ok(loanRepaymentResponses);

    }

    @GetMapping("/{applicationId}/total-paid")
    public ResponseEntity<RepaymentTotalPaidResponse> totalPayment(@PathVariable("applicationId") Integer applicationId){
        RepaymentTotalPaidResponse repaymentTotalPaidResponse = loanRepaymentService.totalPaidAmount(applicationId);
        return ResponseEntity.ok(repaymentTotalPaidResponse);
    }

    @GetMapping("{applicationId}/outstanding")
    public ResponseEntity<TotalAmountResponse> totalAmountOutstanding(@PathVariable("applicationId") Integer applicationId){
        TotalAmountResponse totalAmountResponse = loanRepaymentService.totalAmountOutstanding(applicationId);
        return ResponseEntity.ok(totalAmountResponse);
    }

    @GetMapping("{applicationId}/history")
    public ResponseEntity<RepaymentHistoryResponse> repaymentHistory(@PathVariable("applicationId") Integer applicationId){
        RepaymentHistoryResponse repaymentHistoryResponse = loanRepaymentService.repaymentHistory(applicationId);
        return ResponseEntity.ok(repaymentHistoryResponse);
    }

    @PatchMapping("{applicationId}/close")
    public ResponseEntity<ClosedLoanResponse> closedLoan(@PathVariable("applicationId") Integer applicationId){
        ClosedLoanResponse closedLoanResponse = loanRepaymentService.closedLoan(applicationId);
        return ResponseEntity.ok(closedLoanResponse);
    }
}
