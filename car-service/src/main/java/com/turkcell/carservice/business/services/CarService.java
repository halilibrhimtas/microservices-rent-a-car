package com.turkcell.carservice.business.services;

import com.turkcell.carservice.domain.dtos.requests.car.CreateCarRequest;
import com.turkcell.carservice.domain.dtos.requests.car.UpdateCarRequest;
import com.turkcell.carservice.domain.dtos.responses.car.CreateCarResponse;
import com.turkcell.carservice.domain.dtos.responses.car.GetAllCarResponse;
import com.turkcell.carservice.domain.dtos.responses.car.GetCarResponse;
import com.turkcell.carservice.domain.dtos.responses.car.UpdateCarResponse;

import java.util.List;

public interface CarService {
    List<GetAllCarResponse> getAll();
    GetCarResponse getById(String id);
    CreateCarResponse create(CreateCarRequest carRequest);
    UpdateCarResponse update(String id, UpdateCarRequest carRequest);
    void delete(String id);
}
