package com.turkcell.customerservice.business.abstracts;

import com.turkcell.customerservice.business.dtos.requests.CreateCustomerRequest;
import com.turkcell.customerservice.business.dtos.requests.UpdateCustomerRequest;
import com.turkcell.customerservice.business.dtos.responses.CreateCustomerResponse;
import com.turkcell.customerservice.business.dtos.responses.GetAllCustomerResponse;
import com.turkcell.customerservice.business.dtos.responses.GetCustomerResponse;
import com.turkcell.customerservice.business.dtos.responses.UpdateCustomerResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface CustomerService {
    List<GetAllCustomerResponse> getAll();
    GetCustomerResponse getById(UUID id);
    CreateCustomerResponse add(CreateCustomerRequest createCustomerRequest);
    UpdateCustomerResponse update(UUID id, UpdateCustomerRequest updateCustomerRequest);
    void delete(UUID id);
    double getBalance(UUID id);
    double updateBalance(UUID id, double balance);
}
