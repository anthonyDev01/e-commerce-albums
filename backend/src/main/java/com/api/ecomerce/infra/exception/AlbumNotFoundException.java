package com.api.ecomerce.infra.exception;

public class AlbumNotFoundException extends Exception{
    public AlbumNotFoundException(String message) {
        super(message);
    }
}
