package com.turkcell.carservice.business.abstracts;

import com.turkcell.carservice.entities.CarImages;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface CarImagesService {
    List<String> getCarImagesByCarId(String carId);
    CarImages add(String carId, MultipartFile[] files) throws IOException;

    CarImages update(String carId, MultipartFile[] files);
    void delete(String carId);
}
