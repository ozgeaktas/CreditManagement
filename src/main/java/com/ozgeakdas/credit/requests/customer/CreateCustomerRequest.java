package com.ozgeakdas.credit.requests;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class CreateCustomerRequest {
    private String identityNumber;
    private String firstName;
    private String lastName;
    private BigDecimal salary;
    private String phoneNumber;
    private LocalDate birthDate;
    private BigDecimal deposit;
}
