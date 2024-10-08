package com.example.catalog.dto;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Builder
@Data
public class ProductDTO {
    private String id;
    private String name;
    private String category;
    private Integer price;
    private Integer stock;

    public ProductDTO(String id,String name,String category,Integer price,Integer stock){
        this.id=id;
        this.name=name;
        this.category=category;
        this.price=price;
        this.stock=stock;
    }
}
