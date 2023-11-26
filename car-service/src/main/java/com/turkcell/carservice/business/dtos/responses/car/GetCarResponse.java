package com.turkcell.carservice.business.dtos.responses.car;

import com.turkcell.carservice.entities.Brand;
import com.turkcell.carservice.entities.Model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetCarResponse {
    private String id;
    private Model model;
    private Brand brand;
    private int modelYear;
    private double dailyPrice;
    private String color;
    private String plate;
    private boolean available;
    //private String plate;
   // private List<String> images;
}
