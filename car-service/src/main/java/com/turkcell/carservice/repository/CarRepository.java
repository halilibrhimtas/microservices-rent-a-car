package com.turkcell.carservice.repository;

import com.turkcell.carservice.domain.entities.Car;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CarRepository extends MongoRepository<Car, String> {

}
