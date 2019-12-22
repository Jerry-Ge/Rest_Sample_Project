package com.helloworld.helloworld.exceptions;

public class UserServiceException extends RuntimeException{

    private static final long serialVersionUID = 659541180664238186L;

    public UserServiceException(String message) {
        super(message);
    }
}
