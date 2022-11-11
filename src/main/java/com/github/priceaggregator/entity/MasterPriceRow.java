package com.github.priceaggregator.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class MasterPriceRow {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    protected long id;

    @ManyToOne
    protected MasterPrice masterPrice;

    @ManyToOne
    protected Goods goods;

    protected double price;

    protected int amount;

    protected int multiplicity;
}
