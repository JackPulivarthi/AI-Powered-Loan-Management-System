package com.bank.loan_management.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CustomerRequest {

    @NotBlank(message = "Full name cannot be blank")
    private String customerFullName;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Please enter a valid email address")
    private String customerEmailAddress;

    @NotBlank(message = "Mobile number cannot be blank")
    @Pattern(regexp = "^[0-9]{10}$",
            message = "Please enter a valid 10-digit mobile number")
    private String customerMobileNumber;

    @NotNull(message = "Date of birth is required")
    @Past(message = "Date of birth should be in the past")
    private LocalDate customerDateOfBirth;


}
