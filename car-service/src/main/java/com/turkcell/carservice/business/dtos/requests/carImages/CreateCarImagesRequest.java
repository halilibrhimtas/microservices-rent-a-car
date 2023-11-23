package com.turkcell.carservice.business.dtos.requests.carImages;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateCarImagesRequest {
    @NotBlank
    private String carId;

    @Size(min = 1, max = 10, message = "List must have between 1 and 10 elements")
    private List<String> images;
}
