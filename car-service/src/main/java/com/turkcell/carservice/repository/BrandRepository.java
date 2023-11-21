package com.turkcell.carservice.repository;

import com.turkcell.carservice.entities.Brand;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BrandRepository extends MongoRepository<Brand, String> {
   // Brand findBrandById(String id);
    Optional<Brand> findByBrandName(String brandName);
}
