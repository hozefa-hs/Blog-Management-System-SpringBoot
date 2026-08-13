package com.portfolio.BlogManagementSystem.exceptions;


public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
