package com.github.priceaggregator.service.implementations;

import com.github.priceaggregator.entity.MasterPriceRow;
import com.github.priceaggregator.entity.ReadProperties;

import java.nio.file.Path;
import java.util.List;

public class ExcelPriceReader extends AbstractFilePriceReader{

    public ExcelPriceReader(Path filePath, ReadProperties readProperties) {
        super(filePath, readProperties);
    }

    @Override
    public List<MasterPriceRow> readPrice() {
        return null;
    }
}
