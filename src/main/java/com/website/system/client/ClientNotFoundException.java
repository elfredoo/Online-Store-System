package com.website.system.client;

public class ClientNotFoundException extends RuntimeException{
    private static final String DEFAULT_MESSAGE = "Client not found";

    public ClientNotFoundException(String message) {
        super(message);
    }

    public ClientNotFoundException() {
        super(DEFAULT_MESSAGE);
    }
}
