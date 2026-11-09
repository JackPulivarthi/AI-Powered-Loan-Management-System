package com.bank.loan_management.service;

import com.bank.loan_management.dto.CustomerRequest;
import com.bank.loan_management.dto.CustomerResponse;

public interface CustomerService {
    CustomerResponse customerRegistration(CustomerRequest customerRequest);
}
