package com.turkcell.carservice.business.dtos.requests.model;

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
public class UpdateModelRequest {
    @NotBlank
    private String brandId;
    @NotBlank
    @Size(min = 3)
    private String name;
}
