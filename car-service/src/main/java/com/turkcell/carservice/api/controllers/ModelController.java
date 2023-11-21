package com.turkcell.carservice.api.controllers;

import com.turkcell.carservice.business.abstracts.ModelService;
import com.turkcell.carservice.business.dtos.requests.model.CreateModelRequest;
import com.turkcell.carservice.business.dtos.requests.model.UpdateModelRequest;
import com.turkcell.carservice.business.dtos.responses.model.CreateModelResponse;
import com.turkcell.carservice.business.dtos.responses.model.GetAllModelResponse;
import com.turkcell.carservice.business.dtos.responses.model.GetModelResponse;
import com.turkcell.carservice.business.dtos.responses.model.UpdateModelResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/cars/models")
public class ModelController {
    private final ModelService modelService;
    @GetMapping
    public List<GetAllModelResponse> getAll(){
        return modelService.getAll();
    }

    @GetMapping("/getModelById")
    public GetModelResponse getById(@RequestParam("id") String uuid) {
        return modelService.getById(uuid);
    }

    /*
    @GetMapping("/{id}")
    public GetBrandResponse getById(@PathVariable UUID uuid) {
        return brandService.getById(uuid);
    } */

    @PostMapping("/add")
    public CreateModelResponse addCarDto(@RequestBody @Valid CreateModelRequest createModelRequest) {
        return modelService.add(createModelRequest);
    }

    @PutMapping("/update")
    public UpdateModelResponse updateCarDto(@RequestParam("id") String id, @Valid @RequestBody UpdateModelRequest updateModelRequest) {
        return modelService.update(id, updateModelRequest);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam("id") String id) {
        modelService.delete(id);
    }
}
