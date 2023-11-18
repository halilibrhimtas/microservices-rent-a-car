package com.turkcell.carservice.business.conceretes;

import com.turkcell.carservice.business.abstracts.ModelService;
import com.turkcell.carservice.domain.dtos.requests.model.CreateModelRequest;
import com.turkcell.carservice.domain.dtos.requests.model.UpdateModelRequest;
import com.turkcell.carservice.domain.dtos.responses.model.CreateModelResponse;
import com.turkcell.carservice.domain.dtos.responses.model.GetAllModelResponse;
import com.turkcell.carservice.domain.dtos.responses.model.GetModelResponse;
import com.turkcell.carservice.domain.dtos.responses.model.UpdateModelResponse;
import com.turkcell.carservice.domain.entities.Model;
import com.turkcell.carservice.repository.CarRepository;
import com.turkcell.carservice.repository.ModelRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ModelManager implements ModelService {

    private final ModelRepository modelRepository;
    private final ModelMapper modelMapper;
    @Override
    public List<GetAllModelResponse> getAll() {
        List<Model> models = modelRepository.findAll();
        List<GetAllModelResponse> modelResponses = models.stream().map(item -> modelMapper.map(item, GetAllModelResponse.class)).toList();
        return modelResponses;
    }

    @Override
    public GetModelResponse getById(String id) {
        Model model = modelRepository.findById(id).orElseThrow();
        return modelMapper.map(model, GetModelResponse.class);
    }

    @Override
    public CreateModelResponse add(CreateModelRequest request) {
       // Model model = modelMapper.map(request, Model.class);
        Model model = Model.builder().brandId(request.getBrandId()).name(request.getName()).build();
        modelRepository.save(model);
        return modelMapper.map(model, CreateModelResponse.class);
    }

    @Override
    public UpdateModelResponse update(String id, UpdateModelRequest request) {
        Model model = Model.builder().id(id).brandId(request.getBrandId()).name(request.getName()).build();
        modelRepository.save(model);
        return modelMapper.map(model, UpdateModelResponse.class);
    }

    @Override
    public void delete(String id) {
        modelRepository.deleteById(id);
    }
}
