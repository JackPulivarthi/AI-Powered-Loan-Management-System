package com.bank.loan_management.service;

import com.bank.loan_management.dto.CustomerRequest;

public interface CustomerService {
    CustomerRequest customerRegistration(CustomerRequest customerRequest);
}
