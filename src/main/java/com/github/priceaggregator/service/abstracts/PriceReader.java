package com.github.priceaggregator.service.abstracts;

import java.util.List;

public interface PriceReader <T> {
    List<T> readPrice();
}
