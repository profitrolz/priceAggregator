package com.github.priceaggregator.service.abstracts;

import com.github.priceaggregator.dto.MasterPriceRowDto;
import com.github.priceaggregator.entity.MasterPriceRow;
import com.github.priceaggregator.entity.ReadProperties;

import java.util.List;

public interface PriceReader {
    List<MasterPriceRowDto> readPrice();
}
