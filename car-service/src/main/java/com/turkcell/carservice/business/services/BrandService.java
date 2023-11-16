package com.turkcell.carservice.business.services;

import com.turkcell.carservice.domain.dtos.requests.brand.CreateBrandRequest;
import com.turkcell.carservice.domain.dtos.requests.brand.UpdateBrandRequest;
import com.turkcell.carservice.domain.dtos.responses.brand.CreateBrandResponse;
import com.turkcell.carservice.domain.dtos.responses.brand.GetAllBrandResponse;
import com.turkcell.carservice.domain.dtos.responses.brand.GetBrandResponse;
import com.turkcell.carservice.domain.dtos.responses.brand.UpdateBrandResponse;

import java.util.List;

public interface BrandService {
    List<GetAllBrandResponse> getAll();
    GetBrandResponse getById(String id);
    CreateBrandResponse add(CreateBrandRequest request);
    UpdateBrandResponse update(String id, UpdateBrandRequest request);
    void delete(String id);
}
