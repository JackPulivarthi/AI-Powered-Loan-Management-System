package com.bank.loan_management.controller;

import com.bank.loan_management.dto.LoanApplicationRequest;
import com.bank.loan_management.dto.LoanApplicationResponse;
import com.bank.loan_management.service.LoanApplicationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/loan-applications")
public class LoanApplicationController {

    private final LoanApplicationService loanApplicationService;

    LoanApplicationController(LoanApplicationService loanApplicationService){
        this.loanApplicationService = loanApplicationService;
    }

    @PostMapping
    public ResponseEntity<LoanApplicationResponse> loanApplication (@RequestBody @Valid LoanApplicationRequest loanApplicationRequest){
        LoanApplicationResponse loanApplicationResponse = loanApplicationService.loanApplication(loanApplicationRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(loanApplicationResponse);
    }
}
