package com.turkcell.rentalservice.business.conceretes;

import com.turkcell.rentalservice.business.abstracts.RentalService;
import com.turkcell.rentalservice.business.dto.requests.CreateRentalRequest;
import com.turkcell.rentalservice.business.dto.requests.UpdateRentalRequest;
import com.turkcell.rentalservice.business.dto.response.CreateRentalResponse;
import com.turkcell.rentalservice.business.dto.response.GetAllRentalResponse;
import com.turkcell.rentalservice.business.dto.response.GetRentalResponse;
import com.turkcell.rentalservice.business.dto.response.UpdateRentalResponse;
import com.turkcell.rentalservice.business.rules.RentalBusinessRules;
import com.turkcell.rentalservice.entities.Rental;
import com.turkcell.rentalservice.repository.RentalRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RentalManager implements RentalService {

    private final RentalRepository rentalRepository;
    private final ModelMapper modelMapper;
    private final RentalBusinessRules rentalBusinessRules;

    @Override
    public List<GetAllRentalResponse> getAll() {
        List<Rental> rentals = rentalRepository.findAll();
        List<GetAllRentalResponse> allRentalResponses = rentals.stream().map(item -> modelMapper.map(item, GetAllRentalResponse.class)).toList();
        return allRentalResponses;
    }

    @Override
    public GetRentalResponse getById(UUID id) {
        Rental rental = rentalRepository.findById(id).orElseThrow();
        return modelMapper.map(rental, GetRentalResponse.class);
    }

    @Override
    public CreateRentalResponse add(CreateRentalRequest request) {
        rentalBusinessRules.isExistCar(request.getCarId());
        Rental rental = Rental.builder()
                .carId(request.getCarId())
                .startedDate(request.getStartedDate())
                .endDate(request.getEndDate())
                .isRentedCompleted(request.isRentedCompleted())
                .build();
        rentalRepository.save(rental);
        return modelMapper.map(rental, CreateRentalResponse.class);
    }

    @Override
    public UpdateRentalResponse update(UUID id, UpdateRentalRequest request) {
        rentalBusinessRules.isExistRental(id);
        Rental rental = Rental.builder()
                .id(id)
                .carId(request.getCarId())
                .startedDate(request.getStartedDate())
                .endDate(request.getEndDate())
                .isRentedCompleted(request.isRentedCompleted())
                .build();
        rentalRepository.save(rental);
        return modelMapper.map(rental, UpdateRentalResponse.class);
    }

    @Override
    public Boolean checkCarAvailable(String carId) {
        List<Rental> rentals = rentalRepository.findCompletedRentalsByCarId(carId);
        return rentals.isEmpty();
    }

    @Override
    public void delete(UUID id) {
        rentalBusinessRules.isExistRental(id);
        rentalRepository.deleteById(id);
    }
}
