package com.bank.loan_management.repository;

import com.bank.loan_management.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    boolean existsByCustomerEmailAddress(String customerEmailAddress);
}
