package com.turkcell.carservice.repository;

import com.turkcell.carservice.domain.entities.Model;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ModelRepository extends MongoRepository<Model, String> {
    Optional<Model> findById(String id);
}
