package com.website.system.Order;

import com.website.system.Cart.ShoppingCart;
import com.website.system.Client.Client;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Client client;
    @OneToOne
    private ShoppingCart shoppingCart;
    private double totalPrice;
    private LocalDateTime orderDate;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    public Order() {
        this.orderDate = LocalDateTime.now();
    }

    public Order(OrderStatus orderStatus, ShoppingCart shoppingCart, Client client) {
        this.orderStatus = orderStatus;
        this.orderDate = LocalDateTime.now();
        this.shoppingCart = shoppingCart;
        this.client = client;
        this.totalPrice = OrderManager.calculateTotalPrice(shoppingCart);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", client=" + client +
                ", shoppingCart=" + shoppingCart +
                ", totalPrice=" + totalPrice +
                ", orderDate=" + orderDate +
                ", orderStatus=" + orderStatus +
                '}';
    }
}
