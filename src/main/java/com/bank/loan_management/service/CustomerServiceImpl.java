package com.bank.loan_management.service;

import com.bank.loan_management.dto.CustomerRequest;
import com.bank.loan_management.dto.CustomerResponse;
import com.bank.loan_management.entity.Customer;
import com.bank.loan_management.exception.EmailAlreadyExistsException;
import com.bank.loan_management.mapper.Mapper;
import com.bank.loan_management.repository.CustomerRepository;
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

        if(customerRepository.existsByCustomerEmailAddressEqualsIgnoreCase(customerRequest.getCustomerEmailAddress()))
            throw new EmailAlreadyExistsException("Email already registered");

        Customer newCustomer = mapper.mapDTOtoEntity(customerRequest);

        Customer savedCustomer = customerRepository.save(newCustomer);

        return mapper.mapEntityToDTO(savedCustomer);
    }
}
