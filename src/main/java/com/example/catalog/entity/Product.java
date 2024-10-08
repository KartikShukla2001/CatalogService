package com.example.catalog.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collation = "products")
@Data
@EqualsAndHashCode(callSuper = true)
public class Product extends BaseEntity{
    private String name;
    private String category;
    private Integer price;
    private Integer stock;

}
