package com.bank.loan_management.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class CustomerRequest {

    @NotBlank(message = "Full name cannot be blank")
    public String customerFullName;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Please enter a valid email address")
    public String customerEmailAddress;

    @NotBlank(message = "Mobile number cannot be blank")
    @Pattern(regexp = "^[0-9]{10}$",
            message = "Please enter a valid 10-digit mobile number")
    public String customerMobileNumber;

    @NotNull(message = "Date of birth is required")
    @Past(message = "Date of birth should be in the past")
    public LocalDate customerDateOfBirth;


}
