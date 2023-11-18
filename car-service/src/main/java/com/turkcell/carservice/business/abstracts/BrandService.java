package com.turkcell.carservice.business.abstracts;

import com.turkcell.carservice.domain.dtos.requests.brand.CreateBrandRequest;
import com.turkcell.carservice.domain.dtos.requests.brand.UpdateBrandRequest;
import com.turkcell.carservice.domain.dtos.responses.brand.CreateBrandResponse;
import com.turkcell.carservice.domain.dtos.responses.brand.GetAllBrandResponse;
import com.turkcell.carservice.domain.dtos.responses.brand.GetBrandResponse;
import com.turkcell.carservice.domain.dtos.responses.brand.UpdateBrandResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

public interface BrandService {
    List<GetAllBrandResponse> getAll();
    GetBrandResponse getById(String id);
    CreateBrandResponse add(CreateBrandRequest request);
    UpdateBrandResponse update(String id, UpdateBrandRequest request);
    void delete(String id);
}
