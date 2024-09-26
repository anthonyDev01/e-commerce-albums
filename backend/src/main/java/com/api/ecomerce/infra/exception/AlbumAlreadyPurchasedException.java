package com.api.ecomerce.infra.exception;

public class AlbumAlreadyPurchasedException extends Exception{
    public AlbumAlreadyPurchasedException(String message) {
        super(message);
    }
}
