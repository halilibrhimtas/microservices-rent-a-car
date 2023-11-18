package com.turkcell.carservice.repository;

import com.turkcell.carservice.entities.Car;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CarRepository extends MongoRepository<Car, String> {
    Optional<Car> findByPlate(String plate);
}
