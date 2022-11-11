package com.github.priceaggregator.dto;

import lombok.*;
import org.springframework.context.annotation.Scope;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MasterPriceRowDto {
    protected String partNumber;
    protected String name;
    protected String brand;
    protected double price;
    protected int amount;

}
