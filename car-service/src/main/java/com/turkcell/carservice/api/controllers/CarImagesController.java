package com.turkcell.carservice.api.controllers;

import com.turkcell.carservice.business.abstracts.CarImagesService;
import com.turkcell.carservice.entities.CarImages;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@AllArgsConstructor
@Controller
public class CarImagesController {
    private final CarImagesService carImagesService;

    @GetMapping("/api/cars/uploadFormShow")
    public String uploadPage(){
        return "upload_form";
    }
    @PostMapping("/api/cars/upload")
    public String showUploadElements(@RequestParam("carId") String carId, @RequestParam("files") MultipartFile[] files, Model model) throws IOException {
        CarImages carImages = carImagesService.add(carId, files);
        model.addAttribute("carImages", carImages.getImages());
        return "upload_result";
    }
}
