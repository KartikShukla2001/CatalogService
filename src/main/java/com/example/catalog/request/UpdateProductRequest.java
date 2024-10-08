package com.example.catalog.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateProductRequest {
    private final String name;
    private final String category;
    private final Integer price;
    private final Integer stock;

}
