package com.turkcell.carservice.entities;

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
@Document(value = "models")
public class Model {

    @Id
    private String id;
    private String name;
    private String brandId;

}
