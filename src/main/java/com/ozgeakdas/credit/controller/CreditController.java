package com.ozgeakdas.credit.controller;

import com.ozgeakdas.credit.collection.Credit;
import com.ozgeakdas.credit.requests.credit.CreateCreditRequest;
import com.ozgeakdas.credit.requests.credit.UpdateCreditRequest;
import com.ozgeakdas.credit.responses.CreditResultResponse;
import com.ozgeakdas.credit.service.CreditService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/credits")
public class CreditController {
    private final CreditService creditService;

    public CreditController(CreditService creditService) {
        this.creditService = creditService;
    }
    @GetMapping("/getall")
    public List<Credit> getAll(){
        return creditService.getAll();
    }
    @GetMapping("/getbyid/{id}")
    public Credit getById(@PathVariable String id) {
        return creditService.getById(id);
    }
    @PostMapping("/add")
    public void add(@RequestBody CreateCreditRequest createCreditRequest)  {
        creditService.add(createCreditRequest);
    }
    @PutMapping("/update/{id}")
    public void update(@RequestBody UpdateCreditRequest updateCreditRequest,@PathVariable String id)  {
        creditService.update(updateCreditRequest,id);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable String id) {
        creditService.delete(id);

    }
    @GetMapping("/getResult/{id}")
    public CreditResultResponse getResult(@PathVariable String id){
        return creditService.getResult(id);
    }

}
