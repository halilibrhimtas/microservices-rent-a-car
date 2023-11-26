package com.turkcell.rentalservice.business.rules;

import com.turkcell.rentalservice.business.exceptions.BusinessException;
import com.turkcell.rentalservice.entities.Rental;
import com.turkcell.rentalservice.repository.RentalRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class RentalBusinessRules {
    private final RentalRepository rentalRepository;
    private final MessageSource messageSource;
    private final WebClient.Builder webClientBuilder;

    public void isExistRental(UUID id) {
        Optional<Rental> rental = rentalRepository.findById(id);
        if(rental.isEmpty()) {
            throw new BusinessException(messageSource.getMessage("isExistRental", new Object[] {id}, LocaleContextHolder.getLocale()));
        }
    }

    public void isExistCar(String carId) {
        Boolean hasCar = webClientBuilder.build()
                .get()
                .uri("http://car-service/api/cars/check-car",
                        (uriBuilder) -> uriBuilder
                                .queryParam("id", carId).build())
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();
        if(Boolean.FALSE.equals(hasCar)) {
            throw new BusinessException(messageSource.getMessage("isExistCar", new Object[] {carId}, LocaleContextHolder.getLocale()));
        }
    }

    public void isTheBalanceEnoughForTheCar(String carId, UUID customerId) {
        Double carPrice = webClientBuilder.build()
                .get()
                .uri("http://car-service/api/cars/getCarPrice",
                        (uriBuilder) -> uriBuilder
                                .queryParam("id", carId).build())
                .retrieve()
                .bodyToMono(Double.class)
                .block();
        Double balance = webClientBuilder.build()
                .get()
                .uri("http://customer-service/api/customers/getBalance",
                        (uriBuilder) -> uriBuilder
                                .queryParam("id", customerId).build())
                .retrieve()
                .bodyToMono(Double.class)
                .block();

        if(carPrice > balance) {
            throw new BusinessException(messageSource.getMessage("isTheBalanceEnoughForTheCar", new Object[] {}, LocaleContextHolder.getLocale()));
        }
    }

    public void isCarAvailable(String carId) {
        Boolean isAvailable = webClientBuilder.build()
                .get()
                .uri("http://car-service/api/cars/isCarAvailable",
                        (uriBuilder) -> uriBuilder
                                .queryParam("id", carId).build())
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();

        if(!isAvailable) {
            throw new BusinessException(messageSource.getMessage("isCarAvailable", new Object[] {carId}, LocaleContextHolder.getLocale()));

        }
    }
}
