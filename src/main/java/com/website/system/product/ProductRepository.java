package com.website.system.product;

import com.website.system.product.datamodel.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {
    @Query("SELECT p FROM Product p WHERE TYPE(p) = :clazz")
    <T extends Product> List<T> findAllByType(@Param("clazz") Class<T> clazz);
}

