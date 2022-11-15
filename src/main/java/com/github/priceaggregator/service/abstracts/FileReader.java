package com.github.priceaggregator.service.abstracts;

import java.util.List;

public interface FileReader<T> {
    List<T> readFile();
}
