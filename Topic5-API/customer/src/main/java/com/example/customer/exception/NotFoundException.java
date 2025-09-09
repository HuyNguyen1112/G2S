package com.example.customer.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(int id)
    {
        super("Not found with id id: "+ id);
    }

}
