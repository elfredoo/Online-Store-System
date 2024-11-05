package com.website.system.order;

public class ProductOutOfStockException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "Product is out of stock";

    public ProductOutOfStockException(String message) {
        super(message);
    }

    public ProductOutOfStockException() {
        super(DEFAULT_MESSAGE);
    }
}
