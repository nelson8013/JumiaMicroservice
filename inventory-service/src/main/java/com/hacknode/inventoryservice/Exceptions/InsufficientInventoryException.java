package com.hacknode.inventoryservice.Exceptions;

public class InsufficientInventoryException extends RuntimeException {
    public InsufficientInventoryException(String message){
        super(message);
    }

    public InsufficientInventoryException(String message, Throwable cause){
        super(message, cause);
    }
}
