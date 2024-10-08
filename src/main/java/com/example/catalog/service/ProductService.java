package com.example.catalog.service;

import com.example.catalog.bo.ProductsBO;
import com.example.catalog.dto.*;
import com.example.catalog.entity.Product;
import com.example.catalog.enums.ProductCode;
import com.example.catalog.repository.ProductsRepository;
import com.example.catalog.request.AddProductRequest;
import com.example.catalog.request.UpdateProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
public class ProductService {

    ProductsRepository productsRepository;
    ProductsBO productsBO;

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    public ProductService(ProductsRepository productsRepository, ProductsBO productsBO){
        this.productsRepository=productsRepository;
        this.productsBO=productsBO;

    }

    public ProductsResponseDTO getAllProduct(){
        List<Product> products=productsRepository.findAll();
        if(!CollectionUtils.isEmpty(products)){
            List<ProductDTO> productDTOList = new ArrayList<>();
            for(Product product: products){
                ProductDTO productDTO = populateProductDTO(product);
                productDTOList.add(productDTO);
            }
            return new ProductsResponseDTO(ProductCode.SUCCESS.getCode(), ProductCode.SUCCESS.getStatus(),
                    ProductCode.SUCCESS.getStatusMsg(), productDTOList);
        }
        return new ProductsResponseDTO(ProductCode.FAILED_1.getCode(), ProductCode.FAILED_1.getStatus(),
                ProductCode.FAILED_1.getStatusMsg(), null);
    }

    public ProductResponseDTO getProductById(String productId){
        Product product=productsBO.getProductById(productId);
        if(Objects.nonNull(product)){
            return new ProductResponseDTO(ProductCode.SUCCESS.getCode(), ProductCode.SUCCESS.getStatus(),
                    ProductCode.SUCCESS.getStatusMsg(), populateProductDTO(product));
        }

        return new ProductResponseDTO(ProductCode.FAILED_2.getCode(), ProductCode.FAILED_2.getStatus(),
                ProductCode.FAILED_2.getStatusMsg(), null);
    }

    public AddProductResponseDTO addProduct(AddProductRequest addProductRequest){
        if(Objects.nonNull(addProductRequest)){
            String name = null;
            String category = null;
            Integer price = null;
            Integer stock = null;

            if(StringUtils.hasText(addProductRequest.getName()));{
                name=addProductRequest.getName();
            }
            if(StringUtils.hasText(addProductRequest.getCategory()));{
                category=addProductRequest.getCategory();
            }
            if(Objects.nonNull(addProductRequest.getPrice())){
                price=addProductRequest.getPrice();
            }
            if(Objects.nonNull(addProductRequest.getStock())){
                stock=addProductRequest.getPrice();
            }

            Product product = new Product();
            product.setCategory(category);
            product.setName(name);
            product.setPrice(price);
            product.setStock(stock);
            Product product1=productsBO.saveProductToDBAndEraseCache(product);

            return new AddProductResponseDTO(ProductCode.SUCCESS.getCode(), ProductCode.SUCCESS.getStatus(),
                    ProductCode.SUCCESS.getStatusMsg(), populateProductDTO(product1));
        }

        return new AddProductResponseDTO(ProductCode.FAILED_3.getCode(), ProductCode.FAILED_3.getStatus(),
                ProductCode.FAILED_3.getStatusMsg(), null);
    }

    public AddProductResponseDTO updateProduct(String productId, UpdateProductRequest updateProductRequest){
        Optional<Product> product=productsRepository.getById(productId);
        if(product.isPresent() && Objects.nonNull(updateProductRequest)){
            Product product1 = product.get();

            if(StringUtils.hasText(updateProductRequest.getName()));{
                product1.setName(updateProductRequest.getName());
            }
            if(StringUtils.hasText(updateProductRequest.getCategory()));{
                product1.setCategory(updateProductRequest.getCategory());
            }
            if(Objects.nonNull(updateProductRequest.getStock()));{
                product1.setStock(updateProductRequest.getStock());
            }
            if(Objects.nonNull(updateProductRequest.getPrice()));{
                product1.setPrice(updateProductRequest.getPrice());
            }

            Product product2 = productsBO.saveProductToDBAndEraseCache(product1);

            return new AddProductResponseDTO(ProductCode.SUCCESS.getCode(), ProductCode.SUCCESS.getStatus(),
                    ProductCode.SUCCESS.getStatusMsg(), populateProductDTO(product2));
        }

        return new AddProductResponseDTO(ProductCode.FAILED_2.getCode(), ProductCode.FAILED_2.getStatus(),
                ProductCode.FAILED_2.getStatusMsg(), null);
    }

    public DeleteProductResponseDTO deleteProduct(String productId){
        if(productsBO.deleteProductAndEraseCache(productId)) {
            return new DeleteProductResponseDTO(ProductCode.SUCCESS.getCode(), ProductCode.SUCCESS.getStatus(),
                    ProductCode.SUCCESS.getStatusMsg());
        }
        else {
            return new DeleteProductResponseDTO(ProductCode.FAILED_2.getCode(), ProductCode.FAILED_2.getStatus(),
                    ProductCode.FAILED_2.getStatusMsg());
        }
    }

    public ProductsResponseDTO filterProduct(String category, Integer minPrice, Integer maxPrice){
        List<Product> products = productsBO.filterProducts(category, minPrice, maxPrice);
        if(!CollectionUtils.isEmpty(products)){
            List<ProductDTO> productDTOList = new ArrayList<>();
            for(Product product: products){
                ProductDTO productDTO = populateProductDTO(product);
                productDTOList.add(productDTO);
            }
            return new ProductsResponseDTO(ProductCode.SUCCESS.getCode(), ProductCode.SUCCESS.getStatus(),
                    ProductCode.SUCCESS.getStatusMsg(), productDTOList);
        }

        return new ProductsResponseDTO(ProductCode.FAILED_1.getCode(), ProductCode.FAILED_1.getStatus(),
                ProductCode.FAILED_1.getStatusMsg(), null);

    }

    public ProductDTO populateProductDTO(Product product){
        return ProductDTO.builder()
                .id(product.getId().toString())
                .name(product.getName())
                .price(product.getPrice())
                .stock(product.getStock())
                .category(product.getCategory())
                .build();
    }

}
