package com.khaled.demo.exception.customExceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException (String message) {
        super(message);
    }
}
