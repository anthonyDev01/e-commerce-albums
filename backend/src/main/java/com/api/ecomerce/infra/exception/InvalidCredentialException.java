package com.api.ecomerce.infra.exception;

public class InvalidCredentialException extends Exception{
    public InvalidCredentialException(String message) {
        super(message);
    }
}
