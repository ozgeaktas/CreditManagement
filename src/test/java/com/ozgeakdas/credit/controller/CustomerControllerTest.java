package com.ozgeakdas.credit.controller;

import com.ozgeakdas.credit.collection.Customer;
import com.ozgeakdas.credit.requests.customer.CreateCustomerRequest;
import com.ozgeakdas.credit.requests.customer.UpdateCustomerRequest;
import com.ozgeakdas.credit.service.CustomerService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


import static org.mockito.ArgumentMatchers.eq;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
public class CustomerControllerTest {
    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @Test
    public void testGetAllCustomers() throws Exception {
        // Arrange
        List<Customer> customerList = new ArrayList<>();
        customerList.add(new Customer("1", "12345678901", "John", "Doe", 5000, "5551234567", LocalDate.of(1990, 1, 1), 700, null));
        customerList.add(new Customer("2", "23456789012", "Jane", "Doe", 6000, "5552345678", LocalDate.of(1995, 2, 2), 800, null));
        Mockito.when(customerService.getAll()).thenReturn(customerList);

        List<Customer> result=customerController.getAll();

        Assert.assertEquals(customerList,result);


    }

    @Test
    public void testGetCustomerById(){
        Customer customer=new Customer("1", "12345678901", "John", "Doe", 5000, "5551234567", LocalDate.of(1990, 1, 1), 700, null);
        Mockito.when(customerService.getById("1")).thenReturn(customer);

        Customer result=customerController.getById("1");

        Assert.assertEquals(customer,result);

    }

    @Test
    public void testAddCustomer() {
        //Arrange
        CreateCustomerRequest request = new CreateCustomerRequest();
        ArgumentCaptor<CreateCustomerRequest> captor = ArgumentCaptor.forClass(CreateCustomerRequest.class);

        //Act
        customerController.add(request);

        //Assert
        Mockito.verify(customerService).add(captor.capture());
        Assert.assertEquals(request, captor.getValue());
        Assert.assertEquals(request, captor.getValue());
    }
    @Test
    public void testUpdateCredit() {
        //Arrange
        UpdateCustomerRequest request = new UpdateCustomerRequest();
        ArgumentCaptor<UpdateCustomerRequest> captor = ArgumentCaptor.forClass(UpdateCustomerRequest.class);
        String id = "1";

        //Act
        customerController.update(request, id);

        //Assert
        Mockito.verify(customerService).update(captor.capture(), eq(id));
        Assert.assertEquals(request, captor.getValue());
        Assert.assertEquals(request, captor.getValue());
    }
    @Test
    public void testDeleteCredit() {
        //Arrange
        String id = "1";

        //Act
        customerController.delete(id);

        //Assert
        Mockito.verify(customerService).delete(id);
    }


}