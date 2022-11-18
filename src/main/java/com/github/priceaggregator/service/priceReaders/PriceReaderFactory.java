package com.github.priceaggregator.service.priceReaders;

import com.github.priceaggregator.dto.MasterPriceRowDto;
import com.github.priceaggregator.entity.FileType;
import com.github.priceaggregator.entity.PriceFileSource;
import com.github.priceaggregator.entity.ReadProperties;
import com.github.priceaggregator.service.abstracts.FileReader;
import org.springframework.stereotype.Component;

import java.nio.file.Path;

@Component
public class PriceReaderFactory {

    private PriceReaderFactory() {}

    public FileReader<MasterPriceRowDto> getPriceReader(PriceFileSource priceFileSource, ReadProperties readProperties) {

        switch (priceFileSource.getFileType()){
            case XLS:
            case XLSX:
                return new ExcelPriceReader(readProperties, Path.of(priceFileSource.getFilePath(), priceFileSource.getFileName()));
            case CSV:
                throw new IllegalArgumentException("File type not supported");
                //TODO add csv support
            default:
                throw new IllegalArgumentException("File type not supported");
        }
    }
}
