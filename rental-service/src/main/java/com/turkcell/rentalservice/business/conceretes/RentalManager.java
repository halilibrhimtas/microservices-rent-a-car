package com.turkcell.rentalservice.business.conceretes;

import com.turkcell.rentalservice.business.abstracts.RentalService;
import com.turkcell.rentalservice.business.dto.requests.CreateRentalRequest;
import com.turkcell.rentalservice.business.dto.requests.UpdateRentalRequest;
import com.turkcell.rentalservice.business.dto.response.CreateRentalResponse;
import com.turkcell.rentalservice.business.dto.response.GetAllRentalResponse;
import com.turkcell.rentalservice.business.dto.response.GetRentalResponse;
import com.turkcell.rentalservice.business.dto.response.UpdateRentalResponse;
import com.turkcell.rentalservice.business.exceptions.BusinessException;
import com.turkcell.rentalservice.business.rules.RentalBusinessRules;
import com.turkcell.rentalservice.entities.Rental;
import com.turkcell.rentalservice.repository.RentalRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RentalManager implements RentalService {

    private final RentalRepository rentalRepository;
    private final ModelMapper modelMapper;
    private final MessageSource messageSource;
    private final WebClient.Builder webClientBuilder;
    private final RentalBusinessRules rentalBusinessRules;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public List<GetAllRentalResponse> getAll() {
        List<Rental> rentals = rentalRepository.findAll();
        List<GetAllRentalResponse> allRentalResponses = rentals.stream().map(item -> modelMapper.map(item, GetAllRentalResponse.class)).toList();
        return allRentalResponses;
    }

    @Override
    public GetRentalResponse getById(UUID id) {
        Rental rental = rentalRepository.findById(id).orElseThrow();
        return modelMapper.map(rental, GetRentalResponse.class);
    }

    @Override
    public CreateRentalResponse add(CreateRentalRequest request) {
        rentalBusinessRules.isExistCar(request.getCarId());
        rentalBusinessRules.isCarAvailable(request.getCarId());
        rentalBusinessRules.isTheBalanceEnoughForTheCar(request.getCarId(), request.getCustomerId());
        Double carDailyPrice = webClientBuilder.build()
                .get()
                .uri("http://car-service/api/cars/getCarPrice",
                        (uriBuilder) -> uriBuilder
                                .queryParam("id", request.getCarId()).build())
                .retrieve()
                .bodyToMono(Double.class)
                .block();
        Double balance = webClientBuilder.build()
                .get()
                .uri("http://customer-service/api/customers/getBalance",
                        (uriBuilder) -> uriBuilder
                                .queryParam("id", request.getCustomerId()).build())
                .retrieve()
                .bodyToMono(Double.class)
                .block();
        if(balance >= carDailyPrice) {
            Rental rental = Rental.builder()
                    .carId(request.getCarId())
                    .customerId(request.getCustomerId())
                    .startedDate(request.getStartedDate())
                    .endDate(request.getEndDate())
                    .isRentalProcessContinue(request.isRentalProcessContinue())
                    .build();
            rentalRepository.save(rental);
            String message = String.format("Kiralama işlemi başarıyla gerçekleşmiştir.%n" +
                            "Müşteri ID: %s%n" +
                            "Araba ID: %s%n" +
                            "Kiralama Fiyatı: %.2f%n" +
                            "Başlangıç Tarihi: %s%n" +
                            "Bitiş Tarihi: %s%n",
                    request.getCustomerId().toString(), request.getCarId(), carDailyPrice, request.getStartedDate(), request.getEndDate());
            kafkaTemplate.send("rental-topic", message);
            double newBalance = balance - carDailyPrice;
            webClientBuilder.build().put()
                    .uri("http://customer-service/api/customers/updateBalance", (uriBuilder ->
                            uriBuilder
                                    .queryParam("id", request.getCustomerId())
                                    .queryParam("balance", newBalance)
                                    .build())).retrieve().bodyToMono(Double.class).block();
            webClientBuilder.build()
                    .put()
                    .uri("http://car-service/api/cars/updateAvailable",
                            (uriBuilder) -> uriBuilder
                                    .queryParam("id", request.getCarId())
                                    .queryParam("available", false)
                                    .build())
                    .retrieve()
                    .bodyToMono(Boolean.class)
                    .block();
            return modelMapper.map(rental, CreateRentalResponse.class);
        } else {
            throw new BusinessException(messageSource.getMessage("isTheBalanceEnoughForTheCar", new Object[] {}, LocaleContextHolder.getLocale()));
        }
    }

    @Override
    public UpdateRentalResponse update(UUID id, UpdateRentalRequest request) {
        rentalBusinessRules.isExistRental(id);
        Rental rental = Rental.builder()
                .id(id)
                .carId(request.getCarId())
                .startedDate(request.getStartedDate())
                .endDate(request.getEndDate())
                .isRentalProcessContinue(request.isRentalProcessContinue())
                .build();
        rentalRepository.save(rental);
        return modelMapper.map(rental, UpdateRentalResponse.class);
    }

    @Override
    public Boolean checkCarAvailable(String carId) {
        List<Rental> rentals = rentalRepository.findContinueRentalsByCarId(carId);
        return rentals.isEmpty();
    }

    @Override
    public void delete(UUID id) {
        rentalBusinessRules.isExistRental(id);
        rentalRepository.deleteById(id);
    }
}
