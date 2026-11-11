package com.bank.loan_management.mapper;

import com.bank.loan_management.dto.CustomerRequest;
import com.bank.loan_management.dto.CustomerResponse;
import com.bank.loan_management.dto.LoanApplicationResponse;
import com.bank.loan_management.entity.Customer;
import com.bank.loan_management.entity.LoanApplication;
import org.springframework.stereotype.Component;

import java.util.List;

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

    public LoanApplicationResponse mapLoanEntitytoDto(LoanApplication loanApplication){
        LoanApplicationResponse loanApplicationResponse = new LoanApplicationResponse();

        loanApplicationResponse.setApplicationId(loanApplication.getApplicationId());
        loanApplicationResponse.setCustomerId(loanApplication.getCustomer().getCustomerId());
        loanApplicationResponse.setEmi(loanApplication.getEmi());
        loanApplicationResponse.setLoanAmount(loanApplication.getLoanAmount());
        loanApplicationResponse.setMonthlyIncome(loanApplication.getMonthlyIncome());
        loanApplicationResponse.setLoanTenure(loanApplication.getLoanTenure());
        loanApplicationResponse.setStatus(loanApplication.getStatus().name());

        return loanApplicationResponse;
    }

    public List<LoanApplicationResponse> mapLoanEntitiestoDTO(List<LoanApplication> loanApplications){
        if(loanApplications == null)
            return List.of();
        return loanApplications.stream().map(loanApplication -> {
            LoanApplicationResponse loanApplicationResponse= new LoanApplicationResponse();
            loanApplicationResponse.setCustomerId(loanApplication.getCustomer().getCustomerId());
            loanApplicationResponse.setMonthlyIncome(loanApplication.getMonthlyIncome());
            loanApplicationResponse.setStatus(loanApplication.getStatus().name());
            loanApplicationResponse.setLoanTenure(loanApplication.getLoanTenure());
            loanApplicationResponse.setLoanAmount(loanApplication.getLoanAmount());
            loanApplicationResponse.setEmi(loanApplication.getEmi());
            loanApplicationResponse.setApplicationId(loanApplication.getApplicationId());

            return loanApplicationResponse;
        }).toList();

       // return loanApplications.stream().map(this::mapLoanEntitytoDto).toList();
    }
}
