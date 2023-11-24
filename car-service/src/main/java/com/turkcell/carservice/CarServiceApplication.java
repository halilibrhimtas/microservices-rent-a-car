package com.turkcell.carservice;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@SpringBootApplication
public class CarServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(CarServiceApplication.class, args);
	}

}
