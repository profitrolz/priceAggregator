package com.github.priceaggregator.exceptions;

public class SupplierPriceNotFoundException extends RuntimeException {

    public SupplierPriceNotFoundException() {
    }

    public SupplierPriceNotFoundException(String message) {
        super(message);
    }
}
