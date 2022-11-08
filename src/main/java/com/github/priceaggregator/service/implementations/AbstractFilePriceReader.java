package com.github.priceaggregator.service.implementations;

import com.github.priceaggregator.entity.MasterPriceRow;
import com.github.priceaggregator.entity.ReadProperties;
import com.github.priceaggregator.service.abstracts.PriceReader;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.util.List;

@Setter
public abstract class AbstractFilePriceReader implements PriceReader {

    protected Path filePath;

    protected ReadProperties readProperties;

    protected AbstractFilePriceReader(Path filePath, ReadProperties readProperties) {
        this.filePath = filePath;
        this.readProperties = readProperties;
    }

    @Override
    public abstract List<MasterPriceRow> readPrice();
}
