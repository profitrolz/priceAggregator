package com.github.priceaggregator.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
@Data
public class Goods {

    private String name;

    private String partNumber;

    @ManyToOne
    private MasterBrand masterBrand;

}
