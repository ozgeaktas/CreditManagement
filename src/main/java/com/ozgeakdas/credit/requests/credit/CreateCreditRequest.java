package com.ozgeakdas.credit.requests;

import lombok.Data;

@Data
public class CreditCreateRequest {
    private Long id;
    private String result;
    private Integer limit;
    private Long customerId;

}
