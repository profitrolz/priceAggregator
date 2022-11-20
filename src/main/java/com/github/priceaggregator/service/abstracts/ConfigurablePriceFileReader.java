package com.github.priceaggregator.service.abstracts;

import com.github.priceaggregator.entity.FileSource;
import com.github.priceaggregator.entity.ReadProperties;

public interface ConfigurablePriceFileReader<T> extends FileReader<T> {
    void configure(ReadProperties readProperties, FileSource fileSource);

}
