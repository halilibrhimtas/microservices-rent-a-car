package com.turkcell.carservice.business.services;

import com.turkcell.carservice.domain.dtos.requests.model.CreateModelRequest;
import com.turkcell.carservice.domain.dtos.requests.model.UpdateModelRequest;
import com.turkcell.carservice.domain.dtos.responses.model.CreateModelResponse;
import com.turkcell.carservice.domain.dtos.responses.model.GetAllModelResponse;
import com.turkcell.carservice.domain.dtos.responses.model.GetModelResponse;
import com.turkcell.carservice.domain.dtos.responses.model.UpdateModelResponse;

import java.util.List;

public interface ModelService {
    List<GetAllModelResponse> getAll();
    GetModelResponse getById(String id);
    CreateModelResponse add(CreateModelRequest request);
    UpdateModelResponse update(String id, UpdateModelRequest request);
    void delete(String id);
}
