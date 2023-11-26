package com.turkcell.carservice.business.dtos.requests.car;

import com.turkcell.carservice.utils.Regex;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCarRequest {
    @NotBlank
    private String modelId;

    @NotNull
    @Min(1995)
    private int modelYear;

    @NotBlank
    private String color;

    @NotNull(message = "Daily Price değeri boş olamaz")
    @Min(0)
    private double dailyPrice;

    //private CarState carState;

    @Pattern(regexp = Regex.Plate, message = "Türkiye plakası olmalıdır.")
    private String plate;

    private boolean available;

}
