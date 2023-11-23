package com.turkcell.carservice.business.dtos.responses.carImages;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateCarImagesResponse {
    private String id;
    private String carId;
    private List<String> images;
}
