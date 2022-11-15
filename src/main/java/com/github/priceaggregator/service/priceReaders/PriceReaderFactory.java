package com.github.priceaggregator.service.priceReaders;

import com.github.priceaggregator.entity.ReadProperties;
import com.github.priceaggregator.service.abstracts.PriceReader;

import java.nio.file.Path;

public final class PriceReaderFactory {

    private PriceReaderFactory() {}

    public static PriceReader getPriceReader(ReadProperties readProperties, Path path) {

        return new ExcelPriceReader(readProperties, path);
    }
}
