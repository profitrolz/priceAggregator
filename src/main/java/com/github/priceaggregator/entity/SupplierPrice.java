package com.github.priceaggregator.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class SupplierPrice {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    protected long id;

    protected String logo;

    protected boolean needToHandle;

    @ManyToOne
    protected Supplier supplier;

    @Embedded
    protected ReadProperties readProperties;

    @Embedded
    protected FileSource fileSource;
}
