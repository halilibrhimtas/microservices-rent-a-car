package com.turkcell.carservice.repository;

import com.turkcell.carservice.entities.CarImages;
import org.apache.el.stream.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface CarImagesRepository extends MongoRepository<CarImages, String> {
    @Query("{ 'carId' : ?0 }")
    CarImages findCarImagesByCarId(String carId);
    void deleteByCarId(String carId);
}
