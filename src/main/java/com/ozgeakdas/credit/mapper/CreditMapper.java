package com.ozgeakdas.credit.mapper;

import com.ozgeakdas.credit.collection.Credit;
import com.ozgeakdas.credit.requests.credit.CreateCreditRequest;
import com.ozgeakdas.credit.requests.credit.UpdateCreditRequest;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel ="spring")
public interface CreditMapper {
    Credit toCredit(CreateCreditRequest request);
    void update(@MappingTarget Credit credit, UpdateCreditRequest request);
}
