package com.github.priceaggregator.service.abstracts;

import com.github.priceaggregator.entity.FileSource;
import com.github.priceaggregator.entity.ReadProperties;

import java.util.List;

public interface FileReader<T> {
    List<T> readFile();

}
