package com.turkcell.carservice.repository;

import com.turkcell.carservice.domain.entities.Model;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ModelRepository extends MongoRepository<Model, String> {

}
