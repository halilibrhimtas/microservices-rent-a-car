package com.turkcell.rentalservice.business.abstracts;

import com.turkcell.rentalservice.business.dto.requests.CreateRentalRequest;
import com.turkcell.rentalservice.business.dto.requests.UpdateRentalRequest;
import com.turkcell.rentalservice.business.dto.response.CreateRentalResponse;
import com.turkcell.rentalservice.business.dto.response.GetAllRentalResponse;
import com.turkcell.rentalservice.business.dto.response.GetRentalResponse;
import com.turkcell.rentalservice.business.dto.response.UpdateRentalResponse;

import java.util.List;
import java.util.UUID;

public interface RentalService {
    List<GetAllRentalResponse> getAll();
    GetRentalResponse getById(UUID id);
    CreateRentalResponse add(CreateRentalRequest request);
    UpdateRentalResponse update(UUID id, UpdateRentalRequest request);

    Boolean checkCarAvailable(String carId);
    void delete(UUID id);
}
