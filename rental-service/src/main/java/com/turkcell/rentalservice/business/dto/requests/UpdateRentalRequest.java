package com.turkcell.rentalservice.business.dto.requests;

import com.turkcell.rentalservice.utils.Regex;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UpdateRentalRequest {
    @NotBlank
    private String carId;

    @NotBlank
    private UUID customerId;

    // Tarih formatı: dd-mm-yyyy
    @Pattern(regexp = Regex.DATE_REGEX)
    @NotNull
    private Date startedDate;
    @NotNull
    @Pattern(regexp = Regex.DATE_REGEX)
    @Future(message = "Sonlanacağı tarih ileriki bir tarih olmalıdır.")
    private Date endDate;

    @NotNull
    private boolean isRentalProcessContinue;
}
