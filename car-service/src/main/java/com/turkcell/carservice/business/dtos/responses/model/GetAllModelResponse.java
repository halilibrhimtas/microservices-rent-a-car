package com.turkcell.carservice.business.dtos.responses.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetAllModelResponse {
    private String id;
    private String brandId;
    private String name;
}
