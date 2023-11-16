package com.turkcell.carservice.domain.dtos.requests.brand;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UpdateBrandRequest {

    @NotBlank
    @Size(min = 3)
    private String name;
}
