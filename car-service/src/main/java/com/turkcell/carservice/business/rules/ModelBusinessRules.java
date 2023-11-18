package com.turkcell.carservice.business.rules;

import com.turkcell.carservice.business.exceptions.BusinessException;
import com.turkcell.carservice.entities.Model;
import com.turkcell.carservice.repository.ModelRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ModelBusinessRules {
    private final ModelRepository modelRepository;
    private final MessageSource messageSource;

    public void isExistModel(String id) {
        Optional<Model> model = modelRepository.findById(id);
        if(model.isEmpty()){
            throw new BusinessException(messageSource.getMessage("isExistModel", new Object[] {id}, LocaleContextHolder.getLocale()));
        }
    }
    public void modelMustHaveUniqueName(String modelName) {
        Optional<Model> model = modelRepository.findByName(modelName);
        if(model.isPresent()) {
            throw new BusinessException(messageSource.getMessage("modelMustHaveUniqueName", new Object[] {modelName}, LocaleContextHolder.getLocale()));
        }
    }
}
