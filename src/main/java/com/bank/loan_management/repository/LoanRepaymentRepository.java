package com.bank.loan_management.repository;

import com.bank.loan_management.entity.LoanRepayment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanRepaymentRepository extends JpaRepository<LoanRepayment, Integer> {
    List<LoanRepayment> findByLoanApplication_ApplicationIdOrderByInstallmentNumberAsc(Integer applicationId);
}
