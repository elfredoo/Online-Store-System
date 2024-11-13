package com.website.system.product.dto;

import com.website.system.product.ProductManager;
import com.website.system.product.ProductRepository;
import com.website.system.product.datamodel.Product;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductDtoMapper {
    private final ProductRepository productRepository;

    public ProductDtoMapper(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductDto map(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setPrice(product.getPrice());
        productDto.setProductType(product.getProductType());
        if (product.getDiscount()!=null) productDto.setDiscount(product.getDiscount());
        return productDto;
    }

    public Product map(ProductDto productDto) {
        Optional<Product> productById = productRepository.findById(productDto.getId());
        if (productById.isPresent()) {
            return productById.get();
        }
            Product product = new Product();
            product.setName(productDto.getName());
            product.setPrice(productDto.getPrice());
            product.setProductType(productDto.getProductType());
            if (productDto.getDiscount() != null) product.setDiscount(productDto.getDiscount());
            return product;
        }
    }
