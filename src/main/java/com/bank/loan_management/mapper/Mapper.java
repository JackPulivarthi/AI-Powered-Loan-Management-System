package com.bank.loan_management.mapper;

import com.bank.loan_management.dto.CustomerRequest;
import com.bank.loan_management.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Mapper {


    @Autowired
    Customer customer;

    public Customer mapDTOtoEntity(CustomerRequest customerRequest){
        customer.setCustomerFullName(customerRequest.customerFullName);
        customer.setCustomerEmailAddress(customerRequest.customerEmailAddress);
        customer.setCustomerDateOfBirth(customerRequest.customerDateOfBirth);
        customer.setCustomerMobileNumber(customerRequest.customerMobileNumber);

        return customer;
    }

//    public Customer mapEntityToDTO(Customer customer){
//
//        customerRequest.customerFullName=customer.getCustomerFullName();
//        customerRequest.customerEmailAddress=customer.getCustomerEmailAddress();
//        customerRequest.customerDateOfBirth=customer.getCustomerDateOfBirth();
//        customerRequest.customerMobileNumber=customer.getCustomerMobileNumber();
//
//        return customerRequest;
//    }
}
