package com.example.catalog.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AddProductResponseDTO extends BaseDTO{

    private ProductDTO product;

    @Builder(builderMethodName = "baseResponseBuilder")
    @JsonCreator
    public AddProductResponseDTO(Integer code,String status, String message, ProductDTO product){
        super(code,status,message);
        this.product=product;
    }
}
