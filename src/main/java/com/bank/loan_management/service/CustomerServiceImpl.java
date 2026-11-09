package com.bank.loan_management.service;

import com.bank.loan_management.dto.CustomerRequest;
import com.bank.loan_management.dto.CustomerResponse;
import com.bank.loan_management.entity.Customer;
import com.bank.loan_management.mapper.Mapper;
import com.bank.loan_management.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService{



    private final Mapper mapper;
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(Mapper mapper, CustomerRepository customerRepository){
        this.mapper = mapper;
        this.customerRepository = customerRepository;
    }


    @Override
    public CustomerResponse customerRegistration(CustomerRequest customerRequest) {
        Customer newCustomer = mapper.mapDTOtoEntity(customerRequest);

        if(existsByCustomerEmailAddress(customerRequest.customerEmailAddress))
            throw new RuntimeException("Email already registered");

        Customer savedCustomer = customerRepository.save(newCustomer);

        return mapper.mapEntityToDTO(savedCustomer);
    }

    boolean existsByCustomerEmailAddress(String email){
        return customerRepository.existsByCustomerEmailAddress(email);
    }
}
