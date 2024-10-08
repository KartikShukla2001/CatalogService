package com.example.catalog.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class ProductsResponseDTO extends BaseDTO{

    private List<ProductDTO> products;

    @Builder(builderMethodName = "baseResponseBuilder")
    @JsonCreator
    public ProductsResponseDTO(@JsonProperty("code") Integer code,
                               @JsonProperty("status") String status,
                               @JsonProperty("message") String message,
                               @JsonProperty("products") List<ProductDTO> products) {
        super(code, status, message);
        this.products = products;
    }

}
