package com.turkcell.carservice.business.dtos.requests.brand;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateBrandRequest {

    @NotBlank
    @Size(min = 3)
    private String name;
}
