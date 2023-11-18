package com.turkcell.carservice.domain.dtos.requests.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateModelRequest {
    @NotBlank
    private String brandId;
    @NotBlank
    @Size(min = 3)
    private String name;
}
