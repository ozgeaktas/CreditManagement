package com.ozgeakdas.credit.repository;

import com.ozgeakdas.credit.collection.Credit;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditRepository extends MongoRepository<Credit,String> {
}
