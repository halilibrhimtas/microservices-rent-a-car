package com.turkcell.carservice.repository;

import com.turkcell.carservice.domain.entities.Brand;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BrandRepository extends MongoRepository<Brand, String> {

}
