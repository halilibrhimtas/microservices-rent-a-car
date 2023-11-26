package com.turkcell.customerservice.business.conceretes;

import com.turkcell.customerservice.business.abstracts.CustomerService;
import com.turkcell.customerservice.business.dtos.requests.CreateCustomerRequest;
import com.turkcell.customerservice.business.dtos.requests.UpdateCustomerRequest;
import com.turkcell.customerservice.business.dtos.responses.CreateCustomerResponse;
import com.turkcell.customerservice.business.dtos.responses.GetAllCustomerResponse;
import com.turkcell.customerservice.business.dtos.responses.GetCustomerResponse;
import com.turkcell.customerservice.business.dtos.responses.UpdateCustomerResponse;
import com.turkcell.customerservice.business.rules.CustomerBusinessRules;
import com.turkcell.customerservice.entities.Customer;
import com.turkcell.customerservice.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerManager implements CustomerService {
    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;
    private final CustomerBusinessRules customerBusinessRules;
    @Override
    public List<GetAllCustomerResponse> getAll() {
        List<Customer> customers = customerRepository.findAll();
        List<GetAllCustomerResponse> allCustomerResponses = customers
                .stream()
                .map(customer -> GetAllCustomerResponse
                        .builder()
                        .id(customer.getId())
                        .firstName(customer.getFirstName())
                        .lastName(customer.getLastName())
                        .balance(customer.getBalance())
                        .password(customer.getPassword())
                        .birthDate(customer.getBirthDate())
                        .build()).toList();
        return allCustomerResponses;
    }

    @Override
    public GetCustomerResponse getById(UUID id) {
        Customer customer = customerRepository.findById(id).orElseThrow();
        return GetCustomerResponse
                .builder()
                .id(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .balance(customer.getBalance())
                .password(customer.getPassword())
                .birthDate(customer.getBirthDate())
                .build();
    }

    @Override
    public CreateCustomerResponse add(CreateCustomerRequest createCustomerRequest) {
        customerBusinessRules.isItAgeAppropriateToRentACar(createCustomerRequest.getBirthDate());
        Customer customer = Customer
                .builder()
                .firstName(createCustomerRequest.getFirstName())
                .lastName(createCustomerRequest.getLastName())
                .balance(createCustomerRequest.getBalance())
                .birthDate(createCustomerRequest.getBirthDate())
                .email(createCustomerRequest.getEmail())
                .password(createCustomerRequest.getPassword())
                .build();
        Customer addedCustomer = customerRepository.save(customer);
        return modelMapper.map(addedCustomer, CreateCustomerResponse.class);
    }

    @Override
    public UpdateCustomerResponse update(UUID id, UpdateCustomerRequest updateCustomerRequest) {
        Customer customer = Customer
                .builder()
                .id(id)
                .firstName(updateCustomerRequest.getFirstName())
                .lastName(updateCustomerRequest.getLastName())
                .balance(updateCustomerRequest.getBalance())
                .birthDate(updateCustomerRequest.getBirthDate())
                .password(updateCustomerRequest.getPassword())
                .build();
        Customer addedCustomer = customerRepository.save(customer);
        return modelMapper.map(addedCustomer, UpdateCustomerResponse.class);
    }

    @Override
    public void delete(UUID id) {
        customerRepository.deleteById(id);
    }

    @Override
    public double getBalance(UUID id) {
        Customer customer = customerRepository.findById(id).orElseThrow();
        return customer.getBalance();
    }

    @Override
    public double updateBalance(UUID id, double balance) {
        customerRepository.updateBalance(id, balance);
        return balance;
    }
}
