package com.turkcell.carservice.repository;

import com.turkcell.carservice.entities.Model;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ModelRepository extends MongoRepository<Model, String> {
    Optional<Model> findByName(String name);
}
