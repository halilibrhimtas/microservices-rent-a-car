package com.turkcell.customerservice.repository;

import com.turkcell.carservice.entities.CarImages;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CarImagesRepository extends MongoRepository<CarImages, String> {
    CarImages findByCarId(String carId);
    void deleteByCarId(String carId);
}
