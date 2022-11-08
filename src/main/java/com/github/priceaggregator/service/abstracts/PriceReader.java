package com.github.priceaggregator.service.abstracts;

import com.github.priceaggregator.entity.MasterPriceRow;

import java.util.List;

public interface PriceReader {
    List<MasterPriceRow> readPrice();
}
