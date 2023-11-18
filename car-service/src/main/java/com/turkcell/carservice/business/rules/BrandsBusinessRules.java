package com.turkcell.carservice.business.rules;

import com.turkcell.carservice.business.exceptions.BusinessException;
import com.turkcell.carservice.entities.Brand;
import com.turkcell.carservice.repository.BrandRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class BrandsBusinessRules {
    private final BrandRepository brandRepository;
    private final MessageSource messageSource;

    public void isExistBrand(String id) {
        Optional<Brand> brand = brandRepository.findById(id);
        if(brand.isEmpty()){
            throw new BusinessException(messageSource.getMessage("isExistBrand", new Object[] {id}, LocaleContextHolder.getLocale()));
        }
    }
    public void brandMustHaveUniqueName(String brandName) {
        Optional<Brand> brand = brandRepository.findByBrandName(brandName);
        if(brand.isPresent()) {
            throw new BusinessException(messageSource.getMessage("brandMustHaveUniqueName", new Object[] {brandName}, LocaleContextHolder.getLocale()));
        }
    }
}
