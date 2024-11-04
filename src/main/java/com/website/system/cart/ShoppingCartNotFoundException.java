package com.website.system.cart;

public class ShoppingCartNotFoundException extends RuntimeException{
    private static final String DEFAULT_MESSAGE = "Shopping cart not found";

    public ShoppingCartNotFoundException(String message) {
        super(message);
    }

    public ShoppingCartNotFoundException() {
        super(DEFAULT_MESSAGE);
    }
}
