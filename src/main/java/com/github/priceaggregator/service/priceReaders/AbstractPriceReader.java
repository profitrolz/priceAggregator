package com.github.priceaggregator.service.priceReaders;

import com.github.priceaggregator.dto.MasterPriceRowDto;
import com.github.priceaggregator.entity.ReadProperties;
import com.github.priceaggregator.service.abstracts.FileReader;

import java.nio.file.Path;


public abstract class AbstractPriceReader implements FileReader<MasterPriceRowDto> {

    protected final ReadProperties readProperties;
    protected final Path filePath;

    protected AbstractPriceReader(ReadProperties readProperties, Path filePath) {
        this.readProperties = readProperties;
        this.filePath = filePath;
    }
}
