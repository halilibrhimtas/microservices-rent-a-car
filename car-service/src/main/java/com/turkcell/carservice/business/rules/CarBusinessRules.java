package com.turkcell.carservice.business.rules;

import com.turkcell.carservice.business.exceptions.BusinessException;
import com.turkcell.carservice.entities.Car;
import com.turkcell.carservice.repository.CarRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CarBusinessRules {
    private final CarRepository carRepository;
    private final MessageSource messageSource;
    public void isNotSamePlate(String plate) {
        Optional<Car> car = carRepository.findByPlate(plate);
        if(car.isPresent()) {
            throw new BusinessException(messageSource.getMessage("isNotSamePlate", new Object[] {plate}, LocaleContextHolder.getLocale()));
        }
    }

    public void isExistCar(String id) {
        Optional<Car> car = carRepository.findById(id);
        if(car.isEmpty()) {
            throw new BusinessException(messageSource.getMessage("isExistCar", new Object[] {id}, LocaleContextHolder.getLocale()));
        }
    }
}
