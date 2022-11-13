package com.github.priceaggregator.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Goods {

    private String name;

    private String partNumber;

    @ManyToOne
    private MasterBrand masterBrand;

}
