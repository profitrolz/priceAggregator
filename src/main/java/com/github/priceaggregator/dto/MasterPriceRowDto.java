package com.github.priceaggregator.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class MasterPriceRowDto {
    protected String partNumber;
    protected String name;
    protected String brand;
    protected double price;
    protected int amount;
}
