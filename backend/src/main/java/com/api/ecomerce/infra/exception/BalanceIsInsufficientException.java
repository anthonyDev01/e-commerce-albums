package com.api.ecomerce.infra.exception;

public class BalanceIsInsufficientException extends Exception{
    public BalanceIsInsufficientException(String message) {
        super(message);
    }
}
