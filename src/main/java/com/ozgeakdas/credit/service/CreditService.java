package com.ozgeakdas.credit.service;

import com.ozgeakdas.credit.collection.Credit;

import com.ozgeakdas.credit.collection.Customer;
import com.ozgeakdas.credit.mapper.CreditMapper;
import com.ozgeakdas.credit.repository.CreditRepository;
import com.ozgeakdas.credit.requests.credit.CreateCreditRequest;
import com.ozgeakdas.credit.requests.credit.UpdateCreditRequest;
import com.ozgeakdas.credit.responses.CreditResultResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@AllArgsConstructor
public class CreditService {
    CreditRepository creditRepository;
    CustomerService customerService;
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
    public CreditResultResponse getResult(String id) {
        Customer customer = customerService.getById(id);
        Credit credit=new Credit();
        Integer salary = customer.getSalary();
        Integer creditScore = customer.getCreditScore();

        final Integer CREDIT_LIMIT_MULTIPLIER = 4;

        if (creditScore < 500) {
            credit.setResult("REJECT");
            credit.setLimit(0);
            creditRepository.save(credit);

            return mapper.toCreditResultResponse(credit);

        } else if (creditScore >= 500 && creditScore < 1000 && salary<5000  ) {
            credit.setResult("APPROVED");
            credit.setLimit(10000);
            creditRepository.save(credit);

            return mapper.toCreditResultResponse(credit);
        } else if(creditScore >= 500 && creditScore < 1000 && salary >= 5000){
            credit.setResult("APPROVED");
            credit.setLimit(20000);
            creditRepository.save(credit);

            return mapper.toCreditResultResponse(credit);
        } else if(creditScore >= 500 && creditScore < 1000 && salary >= 10000){
            int l=salary*(CREDIT_LIMIT_MULTIPLIER/2);
            credit.setResult("APPROVED");
            credit.setLimit(l);
            creditRepository.save(credit);

            return mapper.toCreditResultResponse(credit);


        } else if(creditScore >= 1000){
            int l =salary*CREDIT_LIMIT_MULTIPLIER;
            credit.setResult("APPROVED");
            credit.setLimit(l);
            creditRepository.save(credit);

            return mapper.toCreditResultResponse(credit);
        }
        return mapper.toCreditResultResponse(credit);
    }





}
