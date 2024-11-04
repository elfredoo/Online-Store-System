package com.website.system.product;

public class ProductNotFoundException extends RuntimeException{
    private static final String DEFAULT_MESSAGE = "Product unavailable.";

    public ProductNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public ProductNotFoundException(String message) {
        super(message);
    }
}
