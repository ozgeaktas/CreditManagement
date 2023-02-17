package com.ozgeakdas.credit.service;

import com.ozgeakdas.credit.collection.Customer;
import com.ozgeakdas.credit.exceptions.NotFoundException;
import com.ozgeakdas.credit.mapper.CustomerMapper;
import com.ozgeakdas.credit.repository.CustomerRepository;
import com.ozgeakdas.credit.requests.customer.CreateCustomerRequest;
import com.ozgeakdas.credit.requests.customer.UpdateCustomerRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class CustomerService {
    CustomerRepository customerRepository;
    CustomerMapper mapper;
    CreditScoreService creditScoreService;


    public List<Customer> getAll() {
        List<Customer> customers=customerRepository.findAll();
        return customers;
    }

    public Customer getById(String id) {
        return customerRepository.findById(id).orElseThrow(() -> new NotFoundException("Customer"));
    }

    public void add(CreateCustomerRequest createCustomerRequest) {
        Customer customer=mapper.toCustomer(createCustomerRequest);
        customer.setCreditScore(creditScoreService.generateCreditScore());
        customerRepository.save(customer);
        log.info("Success adding customer" + customer);
    }

    public void update(UpdateCustomerRequest updateCustomerRequest,String id) {
        Customer customer=customerRepository.findById(id).get();
        mapper.update(customer,updateCustomerRequest);
        customerRepository.save(customer);
        log.info("Success update.");
    }

    public void delete(String id) {
        customerRepository.deleteById(id);

    }
}
