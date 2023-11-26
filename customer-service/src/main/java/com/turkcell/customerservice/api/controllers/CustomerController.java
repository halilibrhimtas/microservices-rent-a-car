package com.turkcell.customerservice.api.controllers;

import com.turkcell.customerservice.business.abstracts.CustomerService;
import com.turkcell.customerservice.business.dtos.requests.CreateCustomerRequest;
import com.turkcell.customerservice.business.dtos.requests.UpdateCustomerRequest;
import com.turkcell.customerservice.business.dtos.responses.CreateCustomerResponse;
import com.turkcell.customerservice.business.dtos.responses.GetAllCustomerResponse;
import com.turkcell.customerservice.business.dtos.responses.GetCustomerResponse;
import com.turkcell.customerservice.business.dtos.responses.UpdateCustomerResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerService customerService;
    @GetMapping
    public List<GetAllCustomerResponse> getAll(){
        return customerService.getAll();
    }

    @GetMapping("/getCustomerById")
    public GetCustomerResponse getById(@RequestParam("id") UUID id) {
        return customerService.getById(id);
    }


    @PostMapping("/register")
    public CreateCustomerResponse add(@RequestBody @Valid CreateCustomerRequest createCustomerRequest){
        return customerService.add(createCustomerRequest);
    }

    @PutMapping("/updateCustomer")
    public UpdateCustomerResponse update(@RequestParam("id") UUID id, @Valid @RequestBody UpdateCustomerRequest updateCustomerRequest){
        return customerService.update(id, updateCustomerRequest);
    }
    @GetMapping("/getBalance")
    public double getCustomerBalance(@RequestParam("id") UUID id) {
        return customerService.getBalance(id);
    }
    @PutMapping("/updateBalance")
    public double updateCustomerBalance(@RequestParam("id") UUID id, @RequestParam("balance") double balance) {
        return customerService.updateBalance(id, balance);
    }
    @DeleteMapping("/deleteCustomer")
    public void delete(@RequestParam("id") UUID id) {
        customerService.delete(id);
    }
}
