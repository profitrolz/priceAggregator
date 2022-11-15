package com.github.priceaggregator.exceptions;

public class SupplierNotFoundException extends RuntimeException {

    public SupplierNotFoundException() {
    }

    public SupplierNotFoundException(String message) {
        super(message);
    }
}
