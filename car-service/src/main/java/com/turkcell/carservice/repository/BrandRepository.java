package com.turkcell.carservice.repository;

import com.turkcell.carservice.entities.Brand;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface BrandRepository extends MongoRepository<Brand, String> {
    Optional<Brand> findByBrandName(String name);
}
