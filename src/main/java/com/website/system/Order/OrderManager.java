package com.website.system.Order;

import com.website.system.Cart.ShoppingCart;
import com.website.system.Product.Product;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OrderManager {
    private final OrderRepository orderRepository;

    public OrderManager(OrderRepository orderRepository) {
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

    public Order placeOrder(ShoppingCart shoppingCart) {
        Order order = new Order();
        order.setClient(shoppingCart.getClient());
        order.setShoppingCart(shoppingCart);
        order.setTotalPrice(calculateTotalPrice(shoppingCart));
        order.setOrderDate(LocalDateTime.now());
        order.setOrderStatus(OrderStatus.NEW);
        orderRepository.save(order);
        return order;
    }
}
