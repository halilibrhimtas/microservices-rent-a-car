package com.turkcell.carservice.business.abstracts;

import com.turkcell.carservice.business.dtos.requests.car.CreateCarRequest;
import com.turkcell.carservice.business.dtos.requests.car.UpdateCarRequest;
import com.turkcell.carservice.business.dtos.responses.car.CreateCarResponse;
import com.turkcell.carservice.business.dtos.responses.car.GetAllCarResponse;
import com.turkcell.carservice.business.dtos.responses.car.GetCarResponse;
import com.turkcell.carservice.business.dtos.responses.car.UpdateCarResponse;

import java.util.List;

public interface CarService {
    List<GetAllCarResponse> getAll();
    GetCarResponse getById(String id);
    CreateCarResponse add(CreateCarRequest carRequest);
    UpdateCarResponse update(String id, UpdateCarRequest carRequest);
    void delete(String id);
}
