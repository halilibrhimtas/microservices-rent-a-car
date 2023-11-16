package com.turkcell.carservice.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Document(value = "cars")
public class Car {
    @Id
    private String id;
    private String modelId;
    private Date modelYear;
    private double dailyPrice;
    private String plate;
    private List<String> imagesUrls;
}
