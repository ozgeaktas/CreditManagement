package com.ozgeakdas.credit.mapper;

import com.ozgeakdas.credit.collection.Customer;
import com.ozgeakdas.credit.requests.customer.CreateCustomerRequest;
import com.ozgeakdas.credit.requests.customer.UpdateCustomerRequest;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel ="spring")
public interface CustomerMapper {
    Customer toCustomer(CreateCustomerRequest request);
    void update(@MappingTarget Customer customer, UpdateCustomerRequest request);




}
