package com.github.priceaggregator.service.priceReaders;

import com.github.priceaggregator.entity.ReadProperties;
import com.github.priceaggregator.service.abstracts.PriceReader;

import java.nio.file.Path;


public abstract class AbstractFilePriceReader implements PriceReader {

    protected final ReadProperties readProperties;
    protected final Path filePath;

    public AbstractFilePriceReader(ReadProperties readProperties, Path filePath) {
        this.readProperties = readProperties;
        this.filePath = filePath;
    }
}
