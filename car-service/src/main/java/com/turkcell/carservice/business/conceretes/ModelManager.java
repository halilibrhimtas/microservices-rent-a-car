package com.turkcell.carservice.business.conceretes;

import com.turkcell.carservice.business.abstracts.ModelService;
import com.turkcell.carservice.business.rules.BrandsBusinessRules;
import com.turkcell.carservice.business.rules.ModelBusinessRules;
import com.turkcell.carservice.business.dtos.requests.model.CreateModelRequest;
import com.turkcell.carservice.business.dtos.requests.model.UpdateModelRequest;
import com.turkcell.carservice.business.dtos.responses.model.CreateModelResponse;
import com.turkcell.carservice.business.dtos.responses.model.GetAllModelResponse;
import com.turkcell.carservice.business.dtos.responses.model.GetModelResponse;
import com.turkcell.carservice.business.dtos.responses.model.UpdateModelResponse;
import com.turkcell.carservice.entities.Model;
import com.turkcell.carservice.repository.ModelRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ModelManager implements ModelService {

    private final ModelRepository modelRepository;
    private final ModelMapper modelMapper;
    private final ModelBusinessRules modelBusinessRules;
    private final BrandsBusinessRules brandsBusinessRules;
    @Override
    public List<GetAllModelResponse> getAll() {
        List<Model> models = modelRepository.findAll();
        List<GetAllModelResponse> modelResponses = models.stream().map(item -> modelMapper.map(item, GetAllModelResponse.class)).toList();
        return modelResponses;
    }

    @Override
    public GetModelResponse getById(String id) {
        modelBusinessRules.isExistModel(id);
        Model model = modelRepository.findById(id).orElseThrow();
        return modelMapper.map(model, GetModelResponse.class);
    }

    @Override
    public CreateModelResponse add(CreateModelRequest request) {
       // Model model = modelMapper.map(request, Model.class);
        modelBusinessRules.modelMustHaveUniqueName(request.getName());
        brandsBusinessRules.isExistBrand(request.getBrandId());
        Model model = Model.builder().brandId(request.getBrandId()).name(request.getName()).build();
        modelRepository.save(model);
        return modelMapper.map(model, CreateModelResponse.class);
    }

    @Override
    public UpdateModelResponse update(String id, UpdateModelRequest request) {
        modelBusinessRules.isExistModel(id);
        brandsBusinessRules.isExistBrand(request.getBrandId());
        modelBusinessRules.modelMustHaveUniqueName(request.getName());
        Model model = Model.builder().id(id).brandId(request.getBrandId()).name(request.getName()).build();
        modelRepository.save(model);
        return modelMapper.map(model, UpdateModelResponse.class);
    }

    @Override
    public void delete(String id) {
        modelBusinessRules.isExistModel(id);
        modelRepository.deleteById(id);
    }
}
