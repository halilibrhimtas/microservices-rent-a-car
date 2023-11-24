package com.turkcell.carservice.business.conceretes;

import com.turkcell.carservice.business.abstracts.CarImagesService;
import com.turkcell.carservice.business.rules.CarImagesBusinessRules;
import com.turkcell.carservice.cloudinary.CloudinaryUploader;
import com.turkcell.carservice.entities.CarImages;
import com.turkcell.carservice.repository.CarImagesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CarImagesManager implements CarImagesService {
    private final CloudinaryUploader cloudinaryUploader;
    private final CarImagesRepository carImagesRepository;
    private final CarImagesBusinessRules carImagesBusinessRules;
    @Override
    public List<String> getCarImagesByCarId(String carId) {
        return null;
    }

    @Override
    public CarImages add(String carId, MultipartFile[] files) throws IOException {
        List<String> imagesUrls = new ArrayList<>();
        carImagesBusinessRules.isExistCarImages(carId);
        for(MultipartFile file : files) {
            String imageUrl = cloudinaryUploader.uploadBase64Image(file);
            imagesUrls.add(imageUrl);
        }
        CarImages carImages = CarImages.builder().carId(carId).images(imagesUrls).build();
        carImagesRepository.save(carImages);
        return carImagesRepository.save(carImages);
    }

    @Override
    public CarImages update(String carId, MultipartFile[] files) {
        return null;
    }

    @Override
    public void delete(String carId) {

    }
}
