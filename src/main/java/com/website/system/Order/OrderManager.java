package com.website.system.Order;

import com.website.system.Cart.CartManager;
import com.website.system.Cart.ShoppingCart;
import com.website.system.Product.Product;
import com.website.system.Product.ProductManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class OrderManager {
    private final ProductManager productManager;
    private final CartManager cartManager;
    private final OrderRepository orderRepository;

    public OrderManager(ProductManager productManager, CartManager cartManager, OrderRepository orderRepository) {
        this.productManager = productManager;
        this.cartManager = cartManager;
        this.orderRepository = orderRepository;
    }

    static double calculateTotalPrice(ShoppingCart cart) {
        if (cart.getProducts().isEmpty()) return 0;
        double totalPrice = 0;
        for (Product product : cart.getProducts()) {
            totalPrice+=product.getPrice();
        }
        return totalPrice;
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElseThrow(()-> new NoSuchElementException("No order found with ID: " + id));
    }

    @Transactional
    public Order placeOrder(Long shoppingCartId) {
        ShoppingCart shoppingCart = cartManager.getShoppingCart(shoppingCartId);
        removeFromStock(shoppingCart.getProducts());
        Order order = new Order();
        order.setClient(shoppingCart.getClient());
        order.setShoppingCart(shoppingCart);
        order.setTotalPrice(calculateTotalPrice(shoppingCart));
        order.setOrderDate(LocalDateTime.now());
        order.setOrderStatus(OrderStatus.NEW);
        orderRepository.save(order);
        return order;
    }

    private void removeFromStock(List<Product> products) {
        for (Product product : products) {
            if (product.getQuantity() <= 0) {
                throw new IllegalStateException("Product " + product.getName() + " is out of stock.");
            } else if (product.getQuantity() <= 1) {
                productManager.deleteProduct(product.getId());
            } else{
                minusQuantity(product);
                productManager.addProduct(product);
            }
        }
    }

    private void minusQuantity(Product product) {
        product.setQuantity(product.getQuantity() - 1);
    }
}
