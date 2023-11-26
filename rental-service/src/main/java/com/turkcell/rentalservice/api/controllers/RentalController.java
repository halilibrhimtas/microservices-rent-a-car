package com.turkcell.rentalservice.api.controllers;

import com.turkcell.rentalservice.business.abstracts.RentalService;
import com.turkcell.rentalservice.business.dto.requests.CreateRentalRequest;
import com.turkcell.rentalservice.business.dto.requests.UpdateRentalRequest;
import com.turkcell.rentalservice.business.dto.response.CreateRentalResponse;
import com.turkcell.rentalservice.business.dto.response.GetAllRentalResponse;
import com.turkcell.rentalservice.business.dto.response.GetRentalResponse;
import com.turkcell.rentalservice.business.dto.response.UpdateRentalResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/rentals")
public class RentalController {
    private final RentalService rentalService;
    @GetMapping
    public List<GetAllRentalResponse> getAll() {
        return rentalService.getAll();
    }

    @GetMapping("/getCarById")
    public GetRentalResponse getById(@RequestParam("id") UUID id) {
        return rentalService.getById(id);
    }

    @GetMapping("/check-car-available")
    public Boolean getAll(@RequestParam("carId") String carId) {
        return rentalService.checkCarAvailable(carId);
    }
    @PostMapping("/add")
    public CreateRentalResponse addCarDto(@RequestBody @Valid CreateRentalRequest createRentalRequest) {
        return rentalService.add(createRentalRequest);
    }

    @PutMapping("/update")
    public UpdateRentalResponse updateCarDto(@RequestParam("id") UUID id, @Valid @RequestBody UpdateRentalRequest updateRentalRequest) {
        return rentalService.update(id, updateRentalRequest);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam("id") UUID id) {
        rentalService.delete(id);
    }
}
