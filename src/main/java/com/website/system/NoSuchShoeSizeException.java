package com.website.system;

public class NoSuchShoeSizeException extends RuntimeException {
    public NoSuchShoeSizeException(String message) {
        super(message);
    }
}
