package com.ozgeakdas.credit.service;

import com.ozgeakdas.credit.collection.Credit;
import com.ozgeakdas.credit.collection.Customer;
import com.ozgeakdas.credit.mapper.CreditMapper;
import com.ozgeakdas.credit.repository.CreditRepository;
import com.ozgeakdas.credit.requests.credit.CreateCreditRequest;
import com.ozgeakdas.credit.requests.credit.UpdateCreditRequest;
import com.ozgeakdas.credit.responses.CreditResultResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CreditServiceTest {

    @InjectMocks
    private CreditService creditService;
    @Mock
    private CustomerService customerService;
    @Mock
    private CreditRepository creditRepository;
    @Mock
    private CreditMapper mapper;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAll_ShouldReturnListOfCredits() {
        // Arrange
        List<Credit> expectedCredits = Arrays.asList(new Credit(), new Credit(), new Credit());
        when(creditRepository.findAll()).thenReturn(expectedCredits);

        // Act
        List<Credit> actualCredits = creditService.getAll();

        // Assert
        assertEquals(expectedCredits, actualCredits);
    }

    @Test
    public void getById_WithValidId_ShouldReturnCorrectCredit() {
        // Arrange
        String id = "123";
        Credit expectedCredit = new Credit();
        when(creditRepository.findById(id)).thenReturn(Optional.of(expectedCredit));

        // Act
        Credit actualCredit = creditService.getById(id);

        // Assert
        assertEquals(expectedCredit, actualCredit);
    }

    @Test
    public void getById_WithInvalidId_ShouldReturnNull() {
        // Arrange
        String id = "123";
        when(creditRepository.findById(id)).thenReturn(Optional.empty());

        // Act
        Credit actualCredit = creditService.getById(id);

        // Assert
        assertNull(actualCredit);
    }

    @Test
    public void add_ShouldSaveCreditToRepository() {
        // Arrange
        CreateCreditRequest createCreditRequest = new CreateCreditRequest();
        Credit credit = new Credit();
        when(mapper.toCredit(createCreditRequest)).thenReturn(credit);

        // Act
        creditService.add(createCreditRequest);

        // Assert
        verify(creditRepository, times(1)).save(credit);
    }

    @Test
    public void update_WithValidId_ShouldUpdateAndSaveCredit() {
        // Arrange
        String id = "123";
        UpdateCreditRequest updateCreditRequest = new UpdateCreditRequest();
        Credit credit = new Credit();
        when(creditRepository.findById(id)).thenReturn(Optional.of(credit));

        // Act
        creditService.update(updateCreditRequest, id);

        // Assert
        verify(mapper, times(1)).update(credit, updateCreditRequest);
        verify(creditRepository, times(1)).save(credit);
    }

    @Test(expected = NoSuchElementException.class)
    public void update_WithInvalidId_ShouldThrowException() {
        // Arrange
        String id = "123";
        UpdateCreditRequest updateCreditRequest = new UpdateCreditRequest();
        when(creditRepository.findById(id)).thenReturn(Optional.empty());

        // Act
        creditService.update(updateCreditRequest, id);
    }

    @Test
    public void delete_WithValidId_ShouldDeleteCreditFromRepository() {
        // Arrange
        String id = "123";

        // Act
        creditService.delete(id);

        // Assert
        verify(creditRepository, times(1)).deleteById(id);
    }
    @Test
    public void getResult_returnsCreditResultResponse() {
        // given
        String customerId = "123";
        Customer customer = new Customer();
        customer.setId(customerId);
        customer.setSalary(15000);
        customer.setCreditScore(900);

        Credit credit = new Credit();
        credit.setResult("APPROVED");
        credit.setLimit(60000);

        CreditResultResponse creditResultResponse = new CreditResultResponse();
        creditResultResponse.setResult("APPROVED");
        creditResultResponse.setLimit(60000);

        when(customerService.getById(customerId)).thenReturn(customer);
        when(creditRepository.save(any(Credit.class))).thenReturn(credit);
        when(mapper.toCreditResultResponse(any(Credit.class))).thenReturn(creditResultResponse);

        // when
        CreditResultResponse result = creditService.getResult(customerId);

        // then
        verify(customerService).getById(customerId);
        verify(creditRepository).save(any(Credit.class));
        verify(mapper).toCreditResultResponse(any(Credit.class));

        assertEquals(creditResultResponse.getResult(), result.getResult());
        assertEquals(creditResultResponse.getLimit(), result.getLimit());
    }


}