package com.turkcell.carservice.business.rules;

import com.turkcell.carservice.business.exceptions.BusinessException;
import com.turkcell.carservice.entities.Brand;
import com.turkcell.carservice.entities.CarImages;
import com.turkcell.carservice.repository.CarImagesRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CarImagesBusinessRules {
    private final CarImagesRepository carImagesRepository;
    private final MessageSource messageSource;

    public void isExistCarImages(String carId) {
        CarImages carImages = carImagesRepository.findCarImagesByCarId(carId);
        if(carImages != null){
            throw new BusinessException(messageSource.getMessage("isExistCarImages", new Object[] {carId}, LocaleContextHolder.getLocale()));
        }
    }
}
