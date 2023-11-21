package com.turkcell.carservice.business.conceretes;

import com.turkcell.carservice.business.abstracts.BrandService;
import com.turkcell.carservice.business.rules.BrandsBusinessRules;
import com.turkcell.carservice.business.dtos.requests.brand.CreateBrandRequest;
import com.turkcell.carservice.business.dtos.requests.brand.UpdateBrandRequest;
import com.turkcell.carservice.business.dtos.responses.brand.CreateBrandResponse;
import com.turkcell.carservice.business.dtos.responses.brand.GetAllBrandResponse;
import com.turkcell.carservice.business.dtos.responses.brand.GetBrandResponse;
import com.turkcell.carservice.business.dtos.responses.brand.UpdateBrandResponse;
import com.turkcell.carservice.entities.Brand;
import com.turkcell.carservice.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandManager implements BrandService {

    private final BrandRepository brandRepository;
    private final ModelMapper modelMapper;
    private final BrandsBusinessRules brandsBusinessRules;


    @Override
    public List<GetAllBrandResponse> getAll() {
        List<Brand> brands = brandRepository.findAll();
        List<GetAllBrandResponse> allResponse = brands.stream().map(item -> modelMapper.map(item, GetAllBrandResponse.class)).toList();
        return allResponse;
    }

    @Override
    public GetBrandResponse getById(String id) {
        brandsBusinessRules.isExistBrand(id);
        Brand brand = brandRepository.findById(id).orElseThrow();
        return modelMapper.map(brand, GetBrandResponse.class);
    }

    @Override
    public CreateBrandResponse add(CreateBrandRequest request) {
        // Brand brand = modelMapper.map(request, Brand.class);
        brandsBusinessRules.brandMustHaveUniqueName(request.getName());

        Brand brand = Brand.builder().brandName(request.getName()).build();
        brandRepository.save(brand);
        return CreateBrandResponse.builder().id(brand.getId()).name(brand.getBrandName()).build();
    }

    @Override
    public UpdateBrandResponse update(String id, UpdateBrandRequest request) {
        brandsBusinessRules.isExistBrand(id);
        brandsBusinessRules.brandMustHaveUniqueName(request.getName());
        Brand brand = Brand.builder().id(id).brandName(request.getName()).build();
        brandRepository.save(brand);
        return modelMapper.map(brand, UpdateBrandResponse.class);
    }

    @Override
    public void delete(String id) {
        brandsBusinessRules.isExistBrand(id);
        brandRepository.deleteById(id);
    }
}
