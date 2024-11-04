package com.website.system.order;

public class OrderNotFoundException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "Order not found";

    public OrderNotFoundException(String message) {
        super(message);
    }

    public OrderNotFoundException() {
        super(DEFAULT_MESSAGE);
    }
}
