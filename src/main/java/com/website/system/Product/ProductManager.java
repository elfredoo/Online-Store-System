package com.website.system.Product;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductManager {
    private final ProductRepository productRepository;

    public ProductManager(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public <T extends Product> T addProduct(T product) {
        return productRepository.save(product);
    }

    public <T extends Product> void deleteProduct(T product) {
        productRepository.delete(product);
    }

    public <T extends Product> Optional<T> getProductById(Long id, Class<T> clazz) {
        return (Optional<T>) productRepository.findById(id);
    }

    public <T extends Product> List<T> getAllProducts(Class<T> clazz) {
        return productRepository.findAllByType(clazz);
    }

    @Transactional
    public <T extends Product> void placeOrder(List<T> products) {
        for (T product : products) {
            if(product.getQuantity() <= 1) {
                productRepository.delete(product);
            }
            else{
                minusQuantity(product);
                productRepository.save(product);
            }
        }
    }

    private <T extends Product> void minusQuantity(T product) {
        product.setQuantity(product.getQuantity() - 1);
    }
}
