package com.turkcell.carservice.business.conceretes;

import com.turkcell.carservice.business.abstracts.CarService;
import com.turkcell.carservice.business.exceptions.BusinessException;
import com.turkcell.carservice.domain.dtos.requests.car.CreateCarRequest;
import com.turkcell.carservice.domain.dtos.requests.car.UpdateCarRequest;
import com.turkcell.carservice.domain.dtos.responses.car.CreateCarResponse;
import com.turkcell.carservice.domain.dtos.responses.car.GetAllCarResponse;
import com.turkcell.carservice.domain.dtos.responses.car.GetCarResponse;
import com.turkcell.carservice.domain.dtos.responses.car.UpdateCarResponse;
import com.turkcell.carservice.domain.entities.Brand;
import com.turkcell.carservice.domain.entities.Car;
import com.turkcell.carservice.domain.entities.Model;
import com.turkcell.carservice.repository.CarRepository;
import com.turkcell.carservice.repository.ModelRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CarManager implements CarService {

    private final CarRepository carRepository;
    private final ModelRepository modelRepository;
    private final ModelMapper modelMapper;
    private final MessageSource messageSource;

    @Override
    public List<GetAllCarResponse> getAll() {
        List<Car> cars = carRepository.findAll();
        List<GetAllCarResponse> allCarResponses = cars.stream().map(item -> modelMapper.map(item, GetAllCarResponse.class)).toList();
        return allCarResponses;
    }

    @Override
    public GetCarResponse getById(String id) {
        Car car = carRepository.findById(id).orElseThrow();
        return modelMapper.map(car, GetCarResponse.class);
    }

    @Override
    public CreateCarResponse add(CreateCarRequest carRequest) {
        //Car car = modelMapper.map(carRequest, Car.class);
        Optional<Model> model = modelRepository.findById(carRequest.getModelId());
        if(model.isPresent()){
            Car car = Car.builder()
                    .modelId(carRequest.getModelId())
                    .modelYear(carRequest.getModelYear())
                    .color(carRequest.getColor())
                    .plate(carRequest.getPlate())
                    .dailyPrice(carRequest.getDailyPrice())
                    .build();
            carRepository.save(car);
            return modelMapper.map(car, CreateCarResponse.class);
        } else {
            throw  new BusinessException(messageSource.getMessage("addCarMustHaveModelId",  new Object[] {model}, LocaleContextHolder.getLocale()));
        }

    }

    @Override
    public UpdateCarResponse update(String id, UpdateCarRequest carRequest) {
        //Car car = modelMapper.map(carRequest, Car.class);
        Car car = Car.builder()
                .id(id)
                .modelId(carRequest.getModelId())
                .modelYear(carRequest.getModelYear())
                .color(carRequest.getColor())
                .plate(carRequest.getPlate())
                .dailyPrice(carRequest.getDailyPrice())
                .build();
        carRepository.save(car);
        return modelMapper.map(car, UpdateCarResponse.class);
    }

    @Override
    public void delete(String id) {
        carRepository.deleteById(id);
    }
}
