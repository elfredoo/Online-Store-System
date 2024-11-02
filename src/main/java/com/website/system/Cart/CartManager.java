package com.website.system.Cart;

import com.website.system.Order.Order;
import com.website.system.Order.OrderStatus;
import com.website.system.Product.Product;
import com.website.system.Product.ProductManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

@Service
public class CartManager {
    private final ShoppingCartRepository shoppingCartRepository;
    private final ProductManager productManager;

    public CartManager(ShoppingCartRepository shoppingCartRepository, ProductManager productManager) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.productManager = productManager;
    }

    @Transactional
    public void addProduct(Long shoppingCartId,Long productId) {
        ShoppingCart shoppingCart = shoppingCartRepository.findById(shoppingCartId).orElseThrow();
        Product product = productManager.getProductById(productId);
        shoppingCart.addProduct(product);
        shoppingCartRepository.save(shoppingCart);
    }

    @Transactional
    public void addProducts(Long shoppingCartId,List<Long> productIds) {
        ShoppingCart shoppingCart = shoppingCartRepository.findById(shoppingCartId).orElseThrow(() -> new NoSuchElementException("Shopping cart does not exist"));
        productIds.forEach(productId -> {
            Product product = productManager.getProductById(productId);
            shoppingCart.addProduct(product);
        });
        shoppingCartRepository.save(shoppingCart);
    }

    public List<Product> getProducts(Long id) {
        ShoppingCart shoppingCart = shoppingCartRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        return shoppingCart.getProducts();
    }

    public ShoppingCart getShoppingCart(Long id) {
        return shoppingCartRepository.findById(id).orElseThrow(()->new NoSuchElementException("No shopping cart found with provided ID:"+id));
    }


}
