package com.turkcell.carservice.domain.dtos.requests.car;

import com.turkcell.carservice.utils.Regex;
import jakarta.validation.constraints.*;

import java.util.Date;
import java.util.List;

public class CreateCarRequest {

    @NotBlank
    private String modelId;

    @Size(min = 10, max = 10)
    private String modelYear;

    @Pattern(regexp = Regex.Plate)
    private String plate;

    @NotNull(message = "Daily Price değeri boş olamaz")
    @Min(0)
    private double dailyPrice;

    //private List<String> imagesUrls;
}
