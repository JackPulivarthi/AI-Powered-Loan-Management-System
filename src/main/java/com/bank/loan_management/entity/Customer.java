package com.bank.loan_management.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Component
public class Customer {

    @Id
    @GeneratedValue
    private Integer customerId;

    @Column(name = "Customer_Full_Name")
    @NotBlank(message = "Full name cannot be blank")
    private String customerFullName;

    @NotBlank(message = "Email address cannot be blank")
    @Column(name = "Customer_Email_Address",unique = true)
    private String customerEmailAddress;

    @Pattern(regexp = "^[0-9]{10}$", message = "Please enter a valid mobile number")
    @Column(name = "Customer_Mobile_Number")
    private String customerMobileNumber;

    @NotNull(message = "Date of birth is required")
    @Past(message = "Date of birth should be past")
    @Column(name = "Customer_Date_Of_Birth")
    private LocalDate customerDateOfBirth;
}
