package com.turkcell.customerservice.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Document(value = "cars")
public class Car {
    @Id
    private String id;
    private String modelId;
    private int modelYear;
    private double dailyPrice;
    private String color;
    private String plate;
    private boolean isAvailable;
    private List<String> images;
}
