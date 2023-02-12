package com.ozgeakdas.credit.service;

import com.ozgeakdas.credit.collection.Credit;

import com.ozgeakdas.credit.mapper.CreditMapper;
import com.ozgeakdas.credit.repository.CreditRepository;
import com.ozgeakdas.credit.requests.credit.CreateCreditRequest;
import com.ozgeakdas.credit.requests.credit.UpdateCreditRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CreditService {
    CreditRepository creditRepository;
    CreditMapper mapper;


    public List<Credit> getAll() {
        List<Credit> credits=creditRepository.findAll();
        return credits;
    }

    public Credit getById(String id) {
        return creditRepository.findById(id).orElse(null);
    }

    public void add(CreateCreditRequest createCreditRequest) {
        Credit credit=mapper.toCredit(createCreditRequest);
        creditRepository.save(credit);
    }

    public void update(UpdateCreditRequest updateCreditRequest, String id) {
        Credit credit=creditRepository.findById(id).get();
        mapper.update(credit,updateCreditRequest);
        creditRepository.save(credit);
    }

    public void delete(String id) {
        creditRepository.deleteById(id);

    }



}
