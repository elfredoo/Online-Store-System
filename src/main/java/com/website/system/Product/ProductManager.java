package com.website.system.Product;

import com.website.system.Product.AntiWrinkleCream.AntiWrinkleCream;
import com.website.system.Product.AntiWrinkleCream.AntiWrinkleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ProductManager {
    private final ProductRepository productRepository;

    public ProductManager(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public <T extends Product> T addProduct(T product) {
        return productRepository.save(product);
    }

    public <T extends Product> void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public <T extends Product> Optional<Product> getProductById(Long id, Class<T> clazz) {
        return productRepository.findById(id);
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public List<Product> getAllProducts() {
        List<Product> productList = new ArrayList<>();
        for (Product product : productRepository.findAll()) {
            productList.add(product);
        }
        return productList;
    }

    public <T extends Product> void updateProduct(Long id, Product product, Class<T> clazz) {
        getProductById(id,clazz).ifPresent(foundProduct->{
            product.setId(foundProduct.getId());
            productRepository.save(product);
        });
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
