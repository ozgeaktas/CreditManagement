package com.ozgeakdas.credit.mapper;

import com.ozgeakdas.credit.collection.Credit;
import com.ozgeakdas.credit.requests.credit.CreateCreditRequest;
import com.ozgeakdas.credit.requests.credit.UpdateCreditRequest;
import com.ozgeakdas.credit.responses.CreditResultResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel ="spring")
public interface CreditMapper {
    @Mapping(source = "customerId",target = "customer.id")
    Credit toCredit(CreateCreditRequest request);
    void update(@MappingTarget Credit credit, UpdateCreditRequest request);
    CreditResultResponse toCreditResultResponse(Credit credit);
}
