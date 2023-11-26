package com.turkcell.customerservice.business.dtos.requests;

import com.turkcell.customerservice.utils.Regex;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCustomerRequest {
    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @Pattern(regexp = Regex.Email)
    private String email;

    @NotBlank
    private String password;

    @Min(0)
    private double balance;

    private Date birthDate;
}
