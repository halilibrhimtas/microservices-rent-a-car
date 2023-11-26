package com.turkcell.customerservice.business.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCustomerResponse {
    private UUID id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private double balance;

    private Date birthDate;
}
