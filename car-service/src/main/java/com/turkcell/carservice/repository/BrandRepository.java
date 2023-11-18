package com.turkcell.carservice.repository;

import com.turkcell.carservice.domain.entities.Brand;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BrandRepository extends MongoRepository<Brand, String> {
   // Brand findBrandById(String id);
}
