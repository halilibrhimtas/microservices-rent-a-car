package com.turkcell.carservice.domain.dtos.responses.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetModelResponse {
    private String id;
    private String brandId;
    private String name;
}
