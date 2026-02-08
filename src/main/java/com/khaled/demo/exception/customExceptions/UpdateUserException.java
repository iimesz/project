package com.khaled.demo.exception.customExceptions;

public class UpdateUserException extends RuntimeException{

    public UpdateUserException (String message) {
        super(message);
    }
}
