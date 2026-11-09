package com.bank.loan_management.controller;

import com.bank.loan_management.dto.CustomerRequest;
import com.bank.loan_management.dto.CustomerResponse;
import com.bank.loan_management.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/customers")
public class CustomerController {


    private final CustomerService customerService;

    public CustomerController(CustomerService customerService){
        this.customerService=customerService;
    }


    @PostMapping
    public CustomerResponse register(@RequestBody @Valid CustomerRequest customerRequest){
        return customerService.customerRegistration(customerRequest);
    }
}
