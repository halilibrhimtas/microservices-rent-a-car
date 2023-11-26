package com.turkcell.rentalservice.business.dto.response;

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
public class UpdateRentalResponse {
    private UUID id;
    private String carId;
    private UUID customerId;
    private Date startedDate;
    private Date endDate;
    private boolean isRentalProcessContinue;
}
