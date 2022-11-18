package com.github.priceaggregator.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Builder
public class NotFoundBrands {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    protected long id;

    @ManyToOne
    protected MasterPrice masterPrice;

    protected String brandName;
}
