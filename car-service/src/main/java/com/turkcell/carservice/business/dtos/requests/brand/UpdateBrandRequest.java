package com.turkcell.carservice.business.dtos.requests.brand;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBrandRequest {

    @NotBlank
    @Size(min = 3)
    private String name;
}
