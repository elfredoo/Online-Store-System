package com.website.system.client;

public class InvalidPasswordException extends RuntimeException{
    private static final String DEFAULT_MESSAGE = "Invalid password";

    public InvalidPasswordException(String message) {
        super(message);
    }

    public InvalidPasswordException() {
        super(DEFAULT_MESSAGE);
    }
}
