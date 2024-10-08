package com.example.catalog.controller;

import com.example.catalog.dto.AddProductResponseDTO;
import com.example.catalog.dto.DeleteProductResponseDTO;
import com.example.catalog.dto.ProductResponseDTO;
import com.example.catalog.dto.ProductsResponseDTO;
import com.example.catalog.request.AddProductRequest;
import com.example.catalog.request.UpdateProductRequest;
import com.example.catalog.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.file.LinkOption;

@RestController
public class ProductController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
    ProductService productService;

    @Autowired
    public ProductController(ProductService productService){
        this.productService=productService;
    }
    @GetMapping("/api/v1/products")
    public ProductsResponseDTO getProducts(){
        LOGGER.info("Request received to get all products");
        return productService.getAllProduct();
    }

    @GetMapping("/api/v1/products/{id}")
    public ProductResponseDTO getProducts(@PathVariable("id") String productId, HttpServletResponse httpServletResponse){
        httpServletResponse.setHeader("X-Cache", "HIT");
        LOGGER.info("Request received to get product by Id: {}", productId);
        return productService.getProductById(productId);
    }

    @PostMapping("/api/v1/products")
    public AddProductResponseDTO addProduct(@RequestBody AddProductRequest addProductRequest){
        LOGGER.info("Request received to add product with details: {}", addProductRequest);
        return productService.addProduct(addProductRequest);
    }

    @PutMapping("/api/v1/products/{id}")
    public AddProductResponseDTO updateProduct(@PathVariable("id") String productId,
                                               @RequestBody UpdateProductRequest updateProductRequest) {
        LOGGER.info("Request received to update product with id: {} with details: {}", productId, updateProductRequest);
        return productService.updateProduct(productId, updateProductRequest);
    }

    @DeleteMapping("api/v1/products/{id}")
    public DeleteProductResponseDTO deleteProduct(@PathVariable("id") String productId){
        LOGGER.info("Received request to delete product with id : {}", productId);
        return productService.deleteProduct(productId);
    }

    @GetMapping("api/v1/products/filter")
    public ProductsResponseDTO filterProducts(@RequestParam("category") String category,
                                              @RequestParam("price_min") Integer minPrice,
                                              @RequestParam("price_max") Integer maxPrice,
                                              HttpServletResponse httpServletResponse) {
        httpServletResponse.setHeader("X-Cache", "HIT");

        LOGGER.info("Request received to get products with category: {} with minPrice: {} and with maxPrice {}",
                category, minPrice, maxPrice);
        return productService.filterProduct(category, minPrice, maxPrice);
    }

    @GetMapping("api/v2/products/filter")
    public ProductsResponseDTO filterProductsV2(@RequestParam("category") String category,
                                              @RequestParam("price_min") Integer minPrice,
                                              @RequestParam("price_max") Integer maxPrice){

        LOGGER.info("Request received to get products with category: {} with minPrice: {} and with maxPrice {}",
                category, minPrice, maxPrice);
        return productService.filterProduct(category, minPrice, maxPrice);
    }

    @GetMapping("/api/v2/products/{id}")
    public ProductResponseDTO getProductsV2(@PathVariable("id") String productId){
        LOGGER.info("Request received to get product by Id: {}", productId);
        return productService.getProductById(productId);
    }

}
