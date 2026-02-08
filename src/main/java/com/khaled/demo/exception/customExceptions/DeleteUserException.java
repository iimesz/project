package com.khaled.demo.exception.customExceptions;

public class DeleteUserException extends RuntimeException{

    public DeleteUserException (String message) {
        super(message);
    }

}
