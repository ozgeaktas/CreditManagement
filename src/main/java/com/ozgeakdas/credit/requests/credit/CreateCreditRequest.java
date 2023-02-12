package com.ozgeakdas.credit.requests.credit;

import lombok.Data;
import org.bson.types.ObjectId;

@Data
public class CreateCreditRequest {
    private String id;
    private String result;
    private Integer limit;
    private String customerId;

}
