package com.bank.loan_management.mapper;

import com.bank.loan_management.dto.CustomerRequest;
import com.bank.loan_management.dto.CustomerResponse;
import com.bank.loan_management.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    public Customer mapDTOtoEntity(CustomerRequest customerRequest){
        Customer customer = new Customer();

        customer.setCustomerFullName(customerRequest.getCustomerFullName());
        customer.setCustomerEmailAddress(customerRequest.getCustomerEmailAddress());
        customer.setCustomerDateOfBirth(customerRequest.getCustomerDateOfBirth());
        customer.setCustomerMobileNumber(customerRequest.getCustomerMobileNumber());

        return customer;
    }

    public CustomerResponse mapEntityToDTO(Customer customer){
        CustomerResponse customerResponse = new CustomerResponse();

        customerResponse.setCustomerId(customer.getCustomerId());
        customerResponse.setCustomerFullName(customer.getCustomerFullName());
        customerResponse.setCustomerEmailAddress(customer.getCustomerEmailAddress());
        customerResponse.setCustomerDateOfBirth(customer.getCustomerDateOfBirth());
        customerResponse.setCustomerMobileNumber(customer.getCustomerMobileNumber());

        return customerResponse;
    }
}
