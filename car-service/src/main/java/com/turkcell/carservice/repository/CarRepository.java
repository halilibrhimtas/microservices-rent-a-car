package com.turkcell.carservice.repository;

import com.turkcell.carservice.domain.entities.Car;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CarRepository extends MongoRepository<Car, String> {

}
