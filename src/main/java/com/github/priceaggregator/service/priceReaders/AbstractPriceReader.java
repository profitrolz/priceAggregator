package com.github.priceaggregator.service.priceReaders;

import com.github.priceaggregator.dto.MasterPriceRowDto;
import com.github.priceaggregator.service.abstracts.ConfigurablePriceFileReader;
import com.github.priceaggregator.service.abstracts.FileReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ResourceUtils;

import java.nio.file.Path;
import java.nio.file.Paths;


public abstract class AbstractPriceReader implements ConfigurablePriceFileReader<MasterPriceRowDto> {

    private static final String DEFAULT_LOCAL_ROOT = "src/test/resources";

    protected String filePath;

    protected String localRootPath;

    protected AbstractPriceReader() {
        this(DEFAULT_LOCAL_ROOT);
    }

    protected AbstractPriceReader (String localRootPath) {
        this.localRootPath = localRootPath;
    }

}
