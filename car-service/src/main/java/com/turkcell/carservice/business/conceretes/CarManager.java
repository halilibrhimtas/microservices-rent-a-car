package com.turkcell.carservice.business.conceretes;

import com.turkcell.carservice.business.abstracts.CarService;
import com.turkcell.carservice.business.rules.BrandsBusinessRules;
import com.turkcell.carservice.business.rules.CarBusinessRules;
import com.turkcell.carservice.business.rules.ModelBusinessRules;
import com.turkcell.carservice.business.dtos.requests.car.CreateCarRequest;
import com.turkcell.carservice.business.dtos.requests.car.UpdateCarRequest;
import com.turkcell.carservice.business.dtos.responses.car.CreateCarResponse;
import com.turkcell.carservice.business.dtos.responses.car.GetAllCarResponse;
import com.turkcell.carservice.business.dtos.responses.car.GetCarResponse;
import com.turkcell.carservice.business.dtos.responses.car.UpdateCarResponse;
import com.turkcell.carservice.entities.Brand;
import com.turkcell.carservice.entities.Car;
import com.turkcell.carservice.entities.Model;
import com.turkcell.carservice.repository.BrandRepository;
import com.turkcell.carservice.repository.CarRepository;
import com.turkcell.carservice.repository.ModelRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarManager implements CarService {

    private final CarRepository carRepository;
    private final ModelRepository modelRepository;
    private final BrandRepository brandRepository;
    private final ModelMapper modelMapper;
    private final MessageSource messageSource;
    private final BrandsBusinessRules brandsBusinessRules;
    private final ModelBusinessRules modelBusinessRules;
    private final CarBusinessRules carBusinessRules;

    @Override
    public List<GetAllCarResponse> getAll() {
        List<Car> cars = carRepository.findAll();
        List<GetAllCarResponse> allCarResponses = cars.stream().map(car -> {
            Model model = modelRepository.findById(car.getModelId()).orElseThrow();
            Brand brand = brandRepository.findById(model.getBrandId()).orElseThrow();
            return GetAllCarResponse
                    .builder()
                    .id(car.getId())
                    .brand(brand)
                    .model(model)
                    .plate(car.getPlate())
                    .color(car.getColor())
                    .dailyPrice(car.getDailyPrice())
                    .modelYear(car.getModelYear())
                    .build();
        }).toList();
        return allCarResponses;
    }

    @Override
    public GetCarResponse getById(String id) {
        Car car = carRepository.findById(id).orElseThrow();
        Model model = modelRepository.findById(car.getModelId()).orElseThrow();
        Brand brand = brandRepository.findById(model.getBrandId()).orElseThrow();
        //return modelMapper.map(car, GetCarResponse.class);
        return  GetCarResponse
                .builder()
                .id(car.getId())
                .brand(brand)
                .model(model)
                .plate(car.getPlate())
                .color(car.getColor())
                .dailyPrice(car.getDailyPrice())
                .modelYear(car.getModelYear())
                .build();
    }

    @Override
    public CreateCarResponse add(CreateCarRequest carRequest) {
        //Car car = modelMapper.map(carRequest, Car.class);
        carBusinessRules.isNotSamePlate(carRequest.getPlate());
        modelBusinessRules.isExistModel(carRequest.getModelId());
        Car car = Car.builder()
                .modelId(carRequest.getModelId())
                .modelYear(carRequest.getModelYear())
                .color(carRequest.getColor())
                .plate(carRequest.getPlate())
                .dailyPrice(carRequest.getDailyPrice())
                .build();
        carRepository.save(car);
        return modelMapper.map(car, CreateCarResponse.class);
    }

    @Override
    public UpdateCarResponse update(String id, UpdateCarRequest carRequest) {
        modelBusinessRules.isExistModel(carRequest.getModelId());
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
        carBusinessRules.isExistCar(id);
        carRepository.deleteById(id);
    }
}
