package com.turkcell.carservice.business.abstracts;

import com.turkcell.carservice.business.dtos.requests.car.CreateCarRequest;
import com.turkcell.carservice.business.dtos.requests.car.UpdateCarRequest;
import com.turkcell.carservice.business.dtos.responses.car.CreateCarResponse;
import com.turkcell.carservice.business.dtos.responses.car.GetAllCarResponse;
import com.turkcell.carservice.business.dtos.responses.car.GetCarResponse;
import com.turkcell.carservice.business.dtos.responses.car.UpdateCarResponse;

import java.io.IOException;
import java.util.List;

public interface CarService {
    List<GetAllCarResponse> getAll();
    GetCarResponse getById(String id);
    CreateCarResponse add(CreateCarRequest carRequest) throws IOException;
    UpdateCarResponse update(String id, UpdateCarRequest carRequest) throws IOException;
    boolean checkCar(String carId);
    double getCarPrice(String carId);
    void delete(String id) throws IOException;

    boolean isCarAvilable(String carId);
    boolean updateCarAvailable(String carId, boolean available);
}
