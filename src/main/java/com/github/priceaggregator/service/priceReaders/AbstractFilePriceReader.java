package com.github.priceaggregator.service.priceReaders;

import com.github.priceaggregator.entity.ReadProperties;
import com.github.priceaggregator.service.abstracts.PriceReader;

import java.nio.file.Path;


public abstract class AbstractFilePriceReader<T> implements PriceReader <T> {

    protected final ReadProperties readProperties;
    protected final Path filePath;

    protected AbstractFilePriceReader(ReadProperties readProperties, Path filePath) {
        this.readProperties = readProperties;
        this.filePath = filePath;
    }
}
