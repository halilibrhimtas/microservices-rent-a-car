package com.turkcell.carservice.business.abstracts;

import com.turkcell.carservice.business.dtos.requests.model.CreateModelRequest;
import com.turkcell.carservice.business.dtos.requests.model.UpdateModelRequest;
import com.turkcell.carservice.business.dtos.responses.model.CreateModelResponse;
import com.turkcell.carservice.business.dtos.responses.model.GetAllModelResponse;
import com.turkcell.carservice.business.dtos.responses.model.GetModelResponse;
import com.turkcell.carservice.business.dtos.responses.model.UpdateModelResponse;

import java.util.List;

public interface ModelService {
    List<GetAllModelResponse> getAll();
    GetModelResponse getById(String id);
    CreateModelResponse add(CreateModelRequest request);
    UpdateModelResponse update(String id, UpdateModelRequest request);
    void delete(String id);
}
