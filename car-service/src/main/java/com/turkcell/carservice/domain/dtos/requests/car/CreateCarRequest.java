package com.turkcell.carservice.domain.dtos.requests.car;

import com.turkcell.carservice.utils.Regex;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateCarRequest {

    @NotBlank
    private String modelId;

    @Min(1995)
    private int modelYear;

    @NotBlank
    private String color;

    @Pattern(regexp = Regex.Plate)
    private String plate;

    @NotNull(message = "Daily Price değeri boş olamaz")
    @Min(0)
    private double dailyPrice;

    //private List<String> imagesUrls;
}
