package com.turkcell.carservice.api.controllers;

import com.turkcell.carservice.business.abstracts.BrandService;
import com.turkcell.carservice.business.dtos.requests.brand.CreateBrandRequest;
import com.turkcell.carservice.business.dtos.requests.brand.UpdateBrandRequest;
import com.turkcell.carservice.business.dtos.responses.brand.CreateBrandResponse;
import com.turkcell.carservice.business.dtos.responses.brand.GetAllBrandResponse;
import com.turkcell.carservice.business.dtos.responses.brand.GetBrandResponse;
import com.turkcell.carservice.business.dtos.responses.brand.UpdateBrandResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/cars/brands")
public class BrandController {
    private final BrandService brandService;

    @GetMapping
    public List<GetAllBrandResponse> getAll(){
        return brandService.getAll();
    }

    @GetMapping("/getBrandById")
    public GetBrandResponse getById(@RequestParam("id") String uuid) {
        return brandService.getById(uuid);
    }

    /*
    @GetMapping("/{id}")
    public GetBrandResponse getById(@PathVariable UUID uuid) {
        return brandService.getById(uuid);
    } */

    @PostMapping("/add")
    public CreateBrandResponse addBrandDto(@RequestBody @Valid CreateBrandRequest createBrandRequest) {
        return brandService.add(createBrandRequest);
    }

    @PutMapping("/update")
    public UpdateBrandResponse updateBrandDto(@RequestParam("id") String id, @Valid @RequestBody UpdateBrandRequest updateBrandRequest) {
        return brandService.update(id, updateBrandRequest);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam("id") String id) {
        brandService.delete(id);
    }

}
