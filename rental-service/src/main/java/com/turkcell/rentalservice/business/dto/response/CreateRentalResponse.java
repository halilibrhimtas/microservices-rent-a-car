package com.turkcell.rentalservice.business.dto.response;

import jakarta.validation.constraints.NotBlank;
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
public class CreateRentalResponse {
    private UUID id;
    private String carId;
    private UUID customerId;
    private Date startedDate;
    private Date endDate;
    private boolean isRentalProcessContinue;
}
