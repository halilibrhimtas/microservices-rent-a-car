package com.turkcell.carservice.controllers;

import com.turkcell.carservice.business.abstracts.CarService;
import com.turkcell.carservice.domain.dtos.requests.car.CreateCarRequest;
import com.turkcell.carservice.domain.dtos.requests.car.UpdateCarRequest;
import com.turkcell.carservice.domain.dtos.responses.car.CreateCarResponse;
import com.turkcell.carservice.domain.dtos.responses.car.GetAllCarResponse;
import com.turkcell.carservice.domain.dtos.responses.car.GetCarResponse;
import com.turkcell.carservice.domain.dtos.responses.car.UpdateCarResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/cars")
public class CarsController {
    private final CarService carService;

    @GetMapping
    public List<GetAllCarResponse> getAll(){
        return carService.getAll();
    }

    @GetMapping("/getCarById")
    public GetCarResponse getById(@RequestParam("id") String uuid) {
        return carService.getById(uuid);
    }

    /*
    @GetMapping("/{id}")
    public GetBrandResponse getById(@PathVariable UUID uuid) {
        return brandService.getById(uuid);
    } */

    @PostMapping("/add")
    public CreateCarResponse addCarDto(@RequestBody @Valid CreateCarRequest createCarRequest) {
        return carService.add(createCarRequest);
    }

    @PutMapping("/update")
    public UpdateCarResponse updateCarDto(@RequestParam("id") String id, @Valid @RequestBody UpdateCarRequest updateCarRequest) {
        return carService.update(id, updateCarRequest);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam("id") String id) {
        carService.delete(id);
    }
}
