package com.github.priceaggregator.service.priceReaders;

import com.github.priceaggregator.dto.MasterPriceRowDto;
import com.github.priceaggregator.entity.FileType;
import com.github.priceaggregator.entity.ReadProperties;
import com.github.priceaggregator.service.abstracts.FileReader;

import java.nio.file.Path;

public final class PriceReaderFactory {

    private PriceReaderFactory() {}

    public static FileReader<MasterPriceRowDto> getPriceReader(FileType fileType, ReadProperties readProperties, Path path) {
        switch (fileType){
            case XLS:
            case XLSX:
                return new ExcelPriceReader(readProperties, path);
            case CSV:
                throw new IllegalArgumentException("File type not supported");
                //TODO add csv support
            default:
                throw new IllegalArgumentException("File type not supported");
        }
    }
}
