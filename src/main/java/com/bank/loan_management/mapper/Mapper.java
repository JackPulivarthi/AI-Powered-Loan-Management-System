package com.bank.loan_management.mapper;

import com.bank.loan_management.dto.CustomerRequest;
import com.bank.loan_management.dto.CustomerResponse;
import com.bank.loan_management.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    public Customer mapDTOtoEntity(CustomerRequest customerRequest){
        Customer customer = new Customer();

        customer.setCustomerFullName(customerRequest.customerFullName);
        customer.setCustomerEmailAddress(customerRequest.customerEmailAddress);
        customer.setCustomerDateOfBirth(customerRequest.customerDateOfBirth);
        customer.setCustomerMobileNumber(customerRequest.customerMobileNumber);

        return customer;
    }

    public CustomerResponse mapEntityToDTO(Customer customer){
        CustomerResponse customerResponse = new CustomerResponse();

        customerResponse.customerId=customer.getCustomerId();
        customerResponse.customerFullName=customer.getCustomerFullName();
        customerResponse.customerEmailAddress=customer.getCustomerEmailAddress();
        customerResponse.customerDateOfBirth=customer.getCustomerDateOfBirth();
        customerResponse.customerMobileNumber=customer.getCustomerMobileNumber();

        return customerResponse;
    }
}
