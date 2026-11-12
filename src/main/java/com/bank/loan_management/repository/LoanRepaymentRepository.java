package com.bank.loan_management.repository;

import com.bank.loan_management.entity.LoanRepayment;
import com.bank.loan_management.enums.RepaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface LoanRepaymentRepository extends JpaRepository<LoanRepayment, Integer> {
    List<LoanRepayment> findByLoanApplication_ApplicationIdOrderByInstallmentNumberAsc(Integer applicationId);
    LoanRepayment findByLoanApplication_ApplicationIdAndRepaymentId(Integer applicationId, Integer repaymentId);
    List<LoanRepayment> findByDueDateBeforeAndPaymentStatus(LocalDate date, RepaymentStatus repaymentStatus);
}
