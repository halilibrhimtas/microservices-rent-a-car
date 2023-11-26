package com.turkcell.carservice.business.conceretes;

import com.turkcell.carservice.business.abstracts.CarService;
import com.turkcell.carservice.business.rules.BrandsBusinessRules;
import com.turkcell.carservice.business.rules.CarBusinessRules;
import com.turkcell.carservice.business.rules.CarImagesBusinessRules;
import com.turkcell.carservice.business.rules.ModelBusinessRules;
import com.turkcell.carservice.business.dtos.requests.car.CreateCarRequest;
import com.turkcell.carservice.business.dtos.requests.car.UpdateCarRequest;
import com.turkcell.carservice.business.dtos.responses.car.CreateCarResponse;
import com.turkcell.carservice.business.dtos.responses.car.GetAllCarResponse;
import com.turkcell.carservice.business.dtos.responses.car.GetCarResponse;
import com.turkcell.carservice.business.dtos.responses.car.UpdateCarResponse;
import com.turkcell.carservice.cloudinary.CloudinaryUploader;
import com.turkcell.carservice.entities.Brand;
import com.turkcell.carservice.entities.Car;
import com.turkcell.carservice.entities.CarImages;
import com.turkcell.carservice.entities.Model;
import com.turkcell.carservice.repository.BrandRepository;
import com.turkcell.carservice.repository.CarImagesRepository;
import com.turkcell.carservice.repository.CarRepository;
import com.turkcell.carservice.repository.ModelRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarManager implements CarService {

    private final CarRepository carRepository;
    private final ModelRepository modelRepository;
    private final BrandRepository brandRepository;
    private final ModelMapper modelMapper;
    private final ModelBusinessRules modelBusinessRules;
    private final CarBusinessRules carBusinessRules;
    private final WebClient.Builder webClientBuilder;
    private final CarImagesBusinessRules carImagesBusinessRules;
    private final BrandsBusinessRules brandsBusinessRules;

    @Override
    public List<GetAllCarResponse> getAll() {
        List<Car> cars = carRepository.findAll();
        List<GetAllCarResponse> allCarResponses = cars.stream().map(car -> {
            modelBusinessRules.isExistModel(car.getModelId());
            Model model = modelRepository.findById(car.getModelId()).orElseThrow();
            brandsBusinessRules.isExistBrand(model.getBrandId());
            Brand brand = brandRepository.findById(model.getBrandId()).orElseThrow();
            //carImagesBusinessRules.isExistCarImages(car.getId());
           // CarImages carImages = getCarImagesByCarId(car.getId());
            return GetAllCarResponse
                    .builder()
                    .id(car.getId())
                    .brand(brand)
                    .model(model)
                    .plate(car.getPlate())
                    .color(car.getColor())
                    .available(car.isAvailable())
                   //.images(carImages.getImages())
                    .dailyPrice(car.getDailyPrice())
                    .modelYear(car.getModelYear())
                    .build();
        }).toList();
        return allCarResponses;
    }

    @Override
    public GetCarResponse getById(String id) {
        Car car = carRepository.findById(id).orElseThrow();
        modelBusinessRules.isExistModel(car.getModelId());
        Model model = modelRepository.findById(car.getModelId()).orElseThrow();
        brandsBusinessRules.isExistBrand(model.getBrandId());
        Brand brand = brandRepository.findById(model.getBrandId()).orElseThrow();
        carImagesBusinessRules.isExistCarImages(car.getId());
        Boolean isAvailable = webClientBuilder.build()
                .get()
                .uri("http://rental-service/api/rentals/check-car-available",
                        (uriBuilder) -> uriBuilder
                                .queryParam("carId", car.getId()).build())
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();
        //return modelMapper.map(car, GetCarResponse.class);
        return  GetCarResponse
                .builder()
                .id(car.getId())
                .brand(brand)
                .model(model)
                .plate(car.getPlate())
                .color(car.getColor())
                .available(isAvailable)
                .dailyPrice(car.getDailyPrice())
                .modelYear(car.getModelYear())
                .build();
    }

    @Override
    public CreateCarResponse add(CreateCarRequest carRequest) throws IOException {
        //Car car = modelMapper.map(carRequest, Car.class);
        carBusinessRules.isNotSamePlate(carRequest.getPlate());
        modelBusinessRules.isExistModel(carRequest.getModelId());
        Car car = Car.builder()
                .modelId(carRequest.getModelId())
                .modelYear(carRequest.getModelYear())
                .color(carRequest.getColor())
                .plate(carRequest.getPlate())
                .dailyPrice(carRequest.getDailyPrice())
                .available(true)
                .build();
        Car createdCAr = carRepository.save(car);
        return CreateCarResponse
                .builder()
                .id(createdCAr.getId())
                .color(createdCAr.getColor())
                .modelId(createdCAr.getModelId())
                .modelYear(createdCAr.getModelYear())
                .available(createdCAr.isAvailable())
                .dailyPrice(createdCAr.getDailyPrice())
                .plate(createdCAr.getPlate())
                .build();
    }

    @Override
    public UpdateCarResponse update(String id, UpdateCarRequest carRequest) throws IOException{
        modelBusinessRules.isExistModel(carRequest.getModelId());
        Car car = Car.builder()
                .id(id)
                .modelId(carRequest.getModelId())
                .modelYear(carRequest.getModelYear())
                .color(carRequest.getColor())
                .plate(carRequest.getPlate())
                .available(carRequest.isAvailable())
                .dailyPrice(carRequest.getDailyPrice())
                .build();
        Car createdCAr = carRepository.save(car);
        return UpdateCarResponse
                .builder()
                .id(createdCAr.getId())
                .color(createdCAr.getColor())
                .dailyPrice(createdCAr.getDailyPrice())
                .plate(createdCAr.getPlate())
                .available(createdCAr.isAvailable())
                .modelYear(createdCAr.getModelYear())
                .build();
    }

    @Override
    public boolean checkCar(String carId) {
        Optional<Car> car = carRepository.findById(carId);
        return car.isPresent();
    }


    @Override
    public double getCarPrice(String carId) {
        carBusinessRules.isExistCar(carId);
        Car car = carRepository.findById(carId).orElseThrow();
        return car.getDailyPrice();
    }

    @Override
    public void delete(String id) throws IOException {
        carBusinessRules.isExistCar(id);
        carRepository.deleteById(id);
    }

    @Override
    public boolean isCarAvilable(String carId) {
        GetCarResponse getCarResponse = getById(carId);
        return getCarResponse.isAvailable();
    }

    @Override
    public boolean updateCarAvailable(String carId, boolean available) {
        Car car = carRepository.findById(carId).orElseThrow();
        car.setAvailable(available);
        carRepository.save(car);
        return available;
    }
}
