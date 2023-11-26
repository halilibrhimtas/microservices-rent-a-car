package com.turkcell.carservice.business.dtos.requests.car;

import com.turkcell.carservice.utils.Regex;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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

    private boolean available;}
