package com.example.catalog.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ProductResponseDTO extends BaseDTO{
    ProductDTO product;

    @Builder(builderMethodName = "baseResponseBuilder")
    @JsonCreator
    public ProductResponseDTO(@JsonProperty("code") Integer code,
                               @JsonProperty("status") String status,
                               @JsonProperty("message") String message,
                               @JsonProperty("product") ProductDTO product) {
        super(code, status, message);
        this.product = product;
    }

}
