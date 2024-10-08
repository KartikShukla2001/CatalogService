package com.example.catalog;

import com.example.catalog.controller.ProductController;
import com.example.catalog.dto.AddProductResponseDTO;
import com.example.catalog.dto.ProductDTO;
import com.example.catalog.dto.ProductResponseDTO;
import com.example.catalog.dto.ProductsResponseDTO;
import com.example.catalog.enums.ProductCode;
import com.example.catalog.request.AddProductRequest;
import com.example.catalog.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ProductControllerTest {
    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductService productService;

    @Mock
    private HttpServletResponse httpServletResponse;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetProducts() {
        // Prepare mock response
        List<ProductDTO> mockProducts = Collections.emptyList();
        ProductsResponseDTO mockResponse =
                new ProductsResponseDTO(ProductCode.SUCCESS.getCode(), ProductCode.SUCCESS.getStatus(), ProductCode.SUCCESS.getStatusMsg(), mockProducts);

        when(productService.getAllProduct()).thenReturn(mockResponse);

        // Call the method
        ProductsResponseDTO response = productController.getProducts();

        // Verify behavior and assertions
        assertEquals(200, response.getCode());
        assertEquals("SUCCESS", response.getStatus());
    }

    @Test
    void testGetProductById() {
        // Prepare mock response
        ProductDTO mockProduct = new ProductDTO("1", "Product 1", "Category", 10, 1);
        ProductResponseDTO mockResponse =
                new ProductResponseDTO(200, "SUCCESS", "Product retrieved successfully", mockProduct);

        when(productService.getProductById("1")).thenReturn(mockResponse);

        // Call the method
        ProductResponseDTO response = productController.getProducts("1", httpServletResponse);

        // Verify behavior and assertions
        assertEquals(200, response.getCode());
        assertEquals("SUCCESS", response.getStatus());
        assertEquals(mockProduct, response.getProduct());
    }

    @Test
    void testAddProduct() {
        // Prepare request and mock response
        AddProductRequest addProductRequest = new AddProductRequest("New Product", "Category", 100, 10);

        AddProductResponseDTO mockResponse =
                new AddProductResponseDTO(200, "SUCCESS", "Product added successfully", null);

        when(productService.addProduct(addProductRequest)).thenReturn(mockResponse);

        // Call the method
        AddProductResponseDTO response = productController.addProduct(addProductRequest);

        // Verify behavior and assertions
        assertEquals(200, response.getCode());
        assertEquals("SUCCESS", response.getStatus());
    }

    @Test
    void testFilterProducts() {
        // Prepare mock response
        ProductDTO mockProduct = new ProductDTO("1", "Product 1", "Category", 10, 1);
        List<ProductDTO> productDTOList = new ArrayList<>();
        productDTOList.add(mockProduct);
        ProductsResponseDTO mockResponse =
                new ProductsResponseDTO(200, "SUCCESS", "Products retrieved successfully", productDTOList);

        when(productService.filterProduct("Category 1", 50, 150)).thenReturn(mockResponse);

        // Call the method
        ProductsResponseDTO response = productController.filterProducts("Category 1", 50, 150, httpServletResponse);

        // Verify behavior and assertions
        assertEquals(200, response.getCode());
        assertEquals("SUCCESS", response.getStatus());
        assertEquals(1, response.getProducts().size());
    }
}
