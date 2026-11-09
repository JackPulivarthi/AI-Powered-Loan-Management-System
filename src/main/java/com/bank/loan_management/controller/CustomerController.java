package com.bank.loan_management.controller;

import com.bank.loan_management.dto.CustomerRequest;
import com.bank.loan_management.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {


    @Autowired
    CustomerService customerService;


    @PostMapping("/customers")
    public CustomerRequest register(@RequestBody CustomerRequest customerRequest){
        return customerService.customerRegistration(customerRequest);
    }
}
