package com.example.catalog.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddProductRequest {
    private final String name;
    private final String category;
    private final Integer price;
    private final Integer stock;

    public AddProductRequest(String name,String category,Integer price,Integer stock){
        this.category=category;
        this.stock=stock;
        this.name=name;
        this.price=price;
    }
}
