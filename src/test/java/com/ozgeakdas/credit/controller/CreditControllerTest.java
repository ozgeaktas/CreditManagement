package com.ozgeakdas.credit.controller;

import com.ozgeakdas.credit.collection.Credit;
import com.ozgeakdas.credit.requests.credit.CreateCreditRequest;
import com.ozgeakdas.credit.requests.credit.UpdateCreditRequest;
import com.ozgeakdas.credit.service.CreditService;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;



@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
public class CreditControllerTest {

    @Mock
    CreditService creditService;

    @InjectMocks
    CreditController creditController;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(creditController).build();
    }

   @Test
    public void getAll_shouldReturnCreditList() {
        //given
        Credit credit1= Credit.builder()
                .id("123")
                .limit(100)
                .result("REJECT").build();
        Credit credit2= Credit.builder()
                .id("1234")
                .limit(200)
                .result("APPROVED").build();
        List<Credit> creditList = new ArrayList<>();
        creditList.add(credit1);
        creditList.add(credit2);

        Mockito.when(creditService.getAll()).thenReturn(creditList);

        //when
        List<Credit> result = creditController.getAll();

        Assert.assertEquals(creditList, result);
    }
    @Test
    public void testGetCreditById() {
        //Arrange
        Credit credit= Credit.builder()
                .id("123")
                .limit(100)
                .result("REJECT").build();
        Mockito.when(creditService.getById("123")).thenReturn(credit);

        //Act
        Credit result = creditController.getById("123");

        //Assert
        Assert.assertEquals(credit, result);
    }
    @Test
    public void testAddCredit() {
        //Arrange
        CreateCreditRequest request = new CreateCreditRequest();
        ArgumentCaptor<CreateCreditRequest> captor = ArgumentCaptor.forClass(CreateCreditRequest.class);

        //Act
        creditController.add(request);

        //Assert
        Mockito.verify(creditService).add(captor.capture());
        Assert.assertEquals(request, captor.getValue());
        Assert.assertEquals(request, captor.getValue());
    }
    @Test
    public void testUpdateCredit() {
        //Arrange
        UpdateCreditRequest request = new UpdateCreditRequest();
        ArgumentCaptor<UpdateCreditRequest> captor = ArgumentCaptor.forClass(UpdateCreditRequest.class);
        String id = "1";

        //Act
        creditController.update(request, id);

        //Assert
        Mockito.verify(creditService).update(captor.capture(), eq(id));
        Assert.assertEquals(request, captor.getValue());
        Assert.assertEquals(request, captor.getValue());
    }
  @Test
  public void testDeleteCredit() {
      //Arrange
      String id = "1";

      //Act
      creditController.delete(id);

      //Assert
      Mockito.verify(creditService).delete(id);
  }



}