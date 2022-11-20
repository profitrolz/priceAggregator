package com.github.priceaggregator.service.priceReaders;

import com.github.priceaggregator.dto.MasterPriceRowDto;
import com.github.priceaggregator.entity.FileSource;
import com.github.priceaggregator.entity.ReadProperties;
import com.github.priceaggregator.service.abstracts.ConfigurablePriceFileReader;
import com.github.priceaggregator.service.abstracts.FileReader;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class PriceReaderFactory {

    private final Map<String, ConfigurablePriceFileReader<MasterPriceRowDto>> fileReaderMap;

    public PriceReaderFactory(Map<String, ConfigurablePriceFileReader<MasterPriceRowDto>> fileReaderMap) {
        this.fileReaderMap = fileReaderMap;
    }

    public FileReader<MasterPriceRowDto> getPriceReader(FileSource fileSource, ReadProperties readProperties) {

        switch (fileSource.getFileType()){
            case XLS:
            case XLSX:
                ConfigurablePriceFileReader<MasterPriceRowDto> reader = fileReaderMap.get("excel");
                reader.configure(readProperties, fileSource);
                return reader;
            case CSV:
                throw new IllegalArgumentException("File type not supported");
                //TODO add csv support
            default:
                throw new IllegalArgumentException("File type not supported");
        }
    }
}
