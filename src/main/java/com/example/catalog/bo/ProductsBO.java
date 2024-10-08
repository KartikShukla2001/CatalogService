package com.example.catalog.bo;

import com.example.catalog.entity.Product;
import com.example.catalog.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class ProductsBO {

    ProductsRepository productsRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    public ProductsBO(ProductsRepository productsRepository){
        this.productsRepository=productsRepository;
    }

    @Cacheable(value = "product", key = "#id")
    public Product getProductById(String productId){
        Optional<Product> product=productsRepository.getById(productId);
        if(product.isPresent()){
            return product.get();
        }
        return null;
    }

    @Cacheable(value = "products", key = "'filter-' + #category + '-' + #minPrice + '-' + #maxPrice")
    public List<Product> filterProducts(String category, Integer minPrice, Integer maxPrice){
        Query query = new Query();
        if(StringUtils.hasText(category)) {
            query.addCriteria(Criteria.where("category").is(category));
        }
        if(Objects.nonNull(minPrice)) {
            query.addCriteria(Criteria.where("price").gte(minPrice));
        }
        if(Objects.nonNull(maxPrice)) {
            query.addCriteria(Criteria.where("price").lte(maxPrice));
        }

        List<Product> products = mongoTemplate.find(query, Product.class);

        return products;

    }

    @CacheEvict(value = {"products", "product"}, allEntries = true)
    public Product saveProductToDBAndEraseCache(Product product) {
        return productsRepository.save(product);
    }

    @CacheEvict(value = {"products", "product"}, allEntries = true)
    public Boolean deleteProductAndEraseCache(String productId) {
        try {
            productsRepository.deleteById(productId);
            return Boolean.TRUE;
        }
        catch (Exception e){
            return Boolean.FALSE;
        }
    }
}
