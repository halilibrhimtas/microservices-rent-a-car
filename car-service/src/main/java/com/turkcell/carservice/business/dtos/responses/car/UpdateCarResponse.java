package com.turkcell.carservice.business.dtos.responses.car;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCarResponse {
    private String id;
    private String modelId;
    private int modelYear;
    private double dailyPrice;
    private String color;
    private String plate;
    private boolean available;
    //private List<String> images;
}
