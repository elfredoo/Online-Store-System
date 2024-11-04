package com.website.system.order;

public class OrderDto {
    private Long id;
    private double totalPrice;
    private OrderStatus orderStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public String toString() {
        return "OrderDto{" +
                "id=" + id +
                ", totalPrice=" + totalPrice +
                ", orderStatus=" + orderStatus +
                '}';
    }
}
