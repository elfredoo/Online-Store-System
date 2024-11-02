package com.website.system.Product;

import com.website.system.Product.AntiWrinkleCream.AntiWrinkleCream;
import com.website.system.Product.AntiWrinkleCream.AntiWrinkleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ProductManager {
    private final AntiWrinkleRepository antiWrinkleRepository;
    private final ProductRepository productRepository;

    public ProductManager(AntiWrinkleRepository antiWrinkleRepository, ProductRepository productRepository) {
        this.antiWrinkleRepository = antiWrinkleRepository;
        this.productRepository = productRepository;
    }

    public AntiWrinkleCream getCream(Long id){
        return antiWrinkleRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    public <T extends Product> T addProduct(T product) {
        return productRepository.save(product);
    }

    public <T extends Product> void deleteProduct(T product) {
        productRepository.delete(product);
    }

    public <T extends Product> Optional<Product> getProductById(Long id, Class<T> clazz) {
        return productRepository.findById(id);
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public <T extends Product> List<T> getAllProducts(Class<T> clazz) {
        return productRepository.findAllByType(clazz);
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
