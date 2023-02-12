package com.ozgeakdas.credit.repository;

import com.ozgeakdas.credit.collection.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends MongoRepository<Customer,String> {

}
