package com.turkcell.carservice.business.conceretes;

import com.turkcell.carservice.business.abstracts.BrandService;
import com.turkcell.carservice.domain.dtos.requests.brand.CreateBrandRequest;
import com.turkcell.carservice.domain.dtos.requests.brand.UpdateBrandRequest;
import com.turkcell.carservice.domain.dtos.responses.brand.CreateBrandResponse;
import com.turkcell.carservice.domain.dtos.responses.brand.GetAllBrandResponse;
import com.turkcell.carservice.domain.dtos.responses.brand.GetBrandResponse;
import com.turkcell.carservice.domain.dtos.responses.brand.UpdateBrandResponse;
import com.turkcell.carservice.domain.entities.Brand;
import com.turkcell.carservice.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BrandManager implements BrandService {

    private final BrandRepository brandRepository;
    private final ModelMapper modelMapper;


    @Override
    public List<GetAllBrandResponse> getAll() {
        List<Brand> brands = brandRepository.findAll();
        List<GetAllBrandResponse> allResponse = brands.stream().map(item -> modelMapper.map(item, GetAllBrandResponse.class)).toList();
        return allResponse;
    }

    @Override
    public GetBrandResponse getById(String id) {
        Brand brand = brandRepository.findById(id).orElseThrow();
        return modelMapper.map(brand, GetBrandResponse.class);
    }

    @Override
    public CreateBrandResponse add(CreateBrandRequest request) {
       // Brand brand = modelMapper.map(request, Brand.class);
        Brand brand = Brand.builder().brandName(request.getName()).build();
        brandRepository.save(brand);
        return modelMapper.map(brand, CreateBrandResponse.class);
    }

    @Override
    public UpdateBrandResponse update(String id, UpdateBrandRequest request) {
        Brand brand = Brand.builder().id(id).brandName(request.getName()).build();
        brandRepository.save(brand);
        return modelMapper.map(brand, UpdateBrandResponse.class);
    }

    @Override
    public void delete(String id) {
        brandRepository.deleteById(id);
    }
}
