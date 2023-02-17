package com.ozgeakdas.credit.service;

import com.ozgeakdas.credit.collection.Customer;
import com.ozgeakdas.credit.mapper.CustomerMapper;
import com.ozgeakdas.credit.repository.CustomerRepository;
import com.ozgeakdas.credit.requests.customer.CreateCustomerRequest;
import com.ozgeakdas.credit.requests.customer.UpdateCustomerRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CustomerServiceTest {

    @InjectMocks
    private CustomerService customerService;
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private CustomerMapper mapper;
    @Mock
    private CreditScoreService creditScoreService;
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void whenCreateCustomerCalledWithValidRequest_itShouldReturnValidCustomer(){

        CreateCustomerRequest createCustomerRequest=new CreateCustomerRequest();
        createCustomerRequest.setIdentityNumber("33327748752");
        createCustomerRequest.setFirstName("John");
        createCustomerRequest.setLastName("Doe");
        createCustomerRequest.setSalary(10000);
        createCustomerRequest.setBirthDate(LocalDate.of(1998,02,1));
        createCustomerRequest.setPhoneNumber("05062474845");
        Customer customer=Customer.builder()
                .id("123456").creditScore(800).firstName("John").lastName("Doe").identityNumber("33327748752").phoneNumber("05062474845").birthDate(LocalDate.of(1998,02,1)).salary(10000).build();

        when(creditScoreService.generateCreditScore()).thenReturn(800);
        when(customerRepository.save(customer)).thenReturn(customer);
        when(mapper.toCustomer(createCustomerRequest)).thenReturn(customer);

       customerService.add(createCustomerRequest);
        ArgumentCaptor<Customer> argument = ArgumentCaptor.forClass(Customer.class);
        verify(customerRepository).save(argument.capture());

        Customer savedCustomer = argument.getValue();
        assertNotNull(savedCustomer.getId());
        assertEquals(createCustomerRequest.getIdentityNumber(), savedCustomer.getIdentityNumber());
        assertEquals(createCustomerRequest.getFirstName(), savedCustomer.getFirstName());
        assertEquals(createCustomerRequest.getLastName(), savedCustomer.getLastName());
        assertEquals(createCustomerRequest.getSalary(), savedCustomer.getSalary());
        assertEquals(createCustomerRequest.getPhoneNumber(), savedCustomer.getPhoneNumber());
        assertEquals(createCustomerRequest.getBirthDate(), savedCustomer.getBirthDate());
        assertEquals(800, savedCustomer.getCreditScore());



    }
    @Test
    public void testUpdateCustomer() {
        // Create a new customer
        Customer customer=Customer.builder()
                .id("123456").creditScore(800).firstName("John").lastName("Doe").identityNumber("33327748752").phoneNumber("05062474845").birthDate(LocalDate.of(1998,02,1)).salary(10000).build();

        customerRepository.save(customer);


        // Update the customer
        UpdateCustomerRequest updateRequest = new UpdateCustomerRequest();
        updateRequest.setFirstName("Jane");
        updateRequest.setLastName("Doe");
        updateRequest.setIdentityNumber("12345678901");
        updateRequest.setSalary(6000);
        updateRequest.setPhoneNumber("5557654321");
        updateRequest.setBirthDate(LocalDate.of(1995, 1, 1));

        when(customerRepository.findById("123456")).thenReturn(Optional.of(customer));
        mapper.update(customer,updateRequest);
        customerRepository.save(customer);



    }
    @Test
    public void testGetById() {
        Customer customer=Customer.builder()
                .id("123456").creditScore(800).firstName("John").lastName("Doe").identityNumber("33327748752").phoneNumber("05062474845").birthDate(LocalDate.of(1998,02,1)).salary(10000).build();
        when(customerRepository.findById(anyString())).thenReturn(Optional.of(customer));

        Customer result = customerService.getById("123456");

        assertNotNull(result);
        assertEquals(customer.getId(), result.getId());
    }
    @Test
    public void testGetAll() {
        List<Customer> customers = new ArrayList<>();
        Customer customer1=Customer.builder()
                .id("123456").creditScore(800).firstName("John").lastName("Doe").identityNumber("33327748752").phoneNumber("05062474845").birthDate(LocalDate.of(1998,02,1)).salary(10000).build();
        Customer customer2=Customer.builder()
                .id("12345").creditScore(700).firstName("Jane").lastName("Doe").identityNumber("333277668752").phoneNumber("0546247484545").birthDate(LocalDate.of(1899,02,1)).salary(80000).build();
        customers.add(customer1);
        customers.add(customer2);
        when(customerRepository.findAll()).thenReturn(customers);

        List<Customer> result = customerService.getAll();

        assertNotNull(result);
        assertEquals(customers.size(), result.size());
    }
    @Test
    public void testDeleteCustomer() {
        // Create a new customer
        Customer customer=Customer.builder()
                .id("123456").creditScore(800).firstName("John").lastName("Doe").identityNumber("33327748752").phoneNumber("05062474845").birthDate(LocalDate.of(1998,02,1)).salary(10000).build();
        customerRepository.save(customer);

        // Delete the customer
        customerService.delete(customer.getId());

        // Verify that the customer has been deleted from the database
        assertFalse(customerRepository.findById(customer.getId()).isPresent());
    }
}