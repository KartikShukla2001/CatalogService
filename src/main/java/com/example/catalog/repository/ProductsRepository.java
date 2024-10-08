package com.example.catalog.repository;
import com.example.catalog.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ProductsRepository extends MongoRepository<Product, String> {

    Optional<Product> getById(String id);

}
