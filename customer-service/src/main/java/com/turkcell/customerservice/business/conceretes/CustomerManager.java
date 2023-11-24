package com.turkcell.customerservice.business.conceretes;

import com.turkcell.customerservice.business.abstracts.CustomerService;
import com.turkcell.customerservice.business.dtos.requests.CreateCustomerRequest;
import com.turkcell.customerservice.business.dtos.requests.UpdateCustomerRequest;
import com.turkcell.customerservice.business.dtos.responses.CreateCustomerResponse;
import com.turkcell.customerservice.business.dtos.responses.GetAllCustomerResponse;
import com.turkcell.customerservice.business.dtos.responses.GetCustomerResponse;
import com.turkcell.customerservice.business.dtos.responses.UpdateCustomerResponse;

import java.util.List;

public class CustomerManager implements CustomerService {
    @Override
    public List<GetAllCustomerResponse> getAll() {
        return null;
    }

    @Override
    public GetCustomerResponse getById(String id) {
        return null;
    }

    @Override
    public CreateCustomerResponse add(CreateCustomerRequest carRequest) {
        return null;
    }

    @Override
    public UpdateCustomerResponse update(String id, UpdateCustomerRequest carRequest) {
        return null;
    }

    @Override
    public void delete(String id) {

    }
}
