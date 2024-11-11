package com.website.system.client;

public class EmailAlreadyTakenException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "Ten email jest już zajęty";

    public EmailAlreadyTakenException(String message) {
        super(message);
    }

    public EmailAlreadyTakenException() {
        super(DEFAULT_MESSAGE);
    }
}
