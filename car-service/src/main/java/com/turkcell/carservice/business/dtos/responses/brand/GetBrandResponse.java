package com.turkcell.carservice.business.dtos.responses.brand;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetBrandResponse {
    private String id;
    private String name;
}
