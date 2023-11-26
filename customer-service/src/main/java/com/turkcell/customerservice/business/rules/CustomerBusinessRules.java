package com.turkcell.customerservice.business.rules;

import com.turkcell.customerservice.business.exceptions.BusinessException;
import com.turkcell.customerservice.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

@Service
@AllArgsConstructor
public class CustomerBusinessRules {
    private final MessageSource messageSource;

    public void isItAgeAppropriateToRentACar(Date birthDate) {
         LocalDate birthLocalDate = birthDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
         LocalDate currentDate = LocalDate.now();
         int age = Period.between(birthLocalDate, currentDate).getYears();

         if(age < 18) {
             throw new BusinessException(messageSource.getMessage("isItAgeAppropriateToRentACar", new Object[] {}, LocaleContextHolder.getLocale()));
         }
    }
}
