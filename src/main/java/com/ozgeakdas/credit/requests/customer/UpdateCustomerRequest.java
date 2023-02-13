package com.ozgeakdas.credit.requests.customer;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;


@Data
public class UpdateCustomerRequest {
    private String identityNumber;
    private String firstName;
    private String lastName;
    private Integer salary;
    private String phoneNumber;
    private LocalDate birthDate;
}
