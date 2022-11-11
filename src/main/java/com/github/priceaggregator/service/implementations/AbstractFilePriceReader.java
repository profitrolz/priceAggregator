package com.github.priceaggregator.service.implementations;

import com.github.priceaggregator.dto.MasterPriceRowDto;
import com.github.priceaggregator.entity.MasterPriceRow;
import com.github.priceaggregator.entity.ReadProperties;
import com.github.priceaggregator.service.abstracts.PriceReader;
import lombok.Setter;

import java.nio.file.Path;
import java.util.List;

public abstract class AbstractFilePriceReader implements PriceReader {

    protected ReadProperties readProperties;

    @Override
    abstract public List<MasterPriceRowDto> readPrice();
}
