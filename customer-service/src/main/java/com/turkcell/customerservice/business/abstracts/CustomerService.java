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

public interface CustomerService {
    List<GetAllCustomerResponse> getAll();
    GetCustomerResponse getById(String id);
    CreateCustomerResponse add(CreateCustomerRequest carRequest);
    UpdateCustomerResponse update(String id, UpdateCustomerRequest carRequest);
    void delete(String id);
}
