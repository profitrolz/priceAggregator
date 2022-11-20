package com.github.priceaggregator.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class MasterPrice {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    protected long id;

    @Builder.Default
    @OneToMany(mappedBy = "masterPrice", cascade = CascadeType.ALL)
    protected List<MasterPriceRow> rows = new ArrayList<>();

    @OneToOne
    protected SupplierPrice supplierPrice;

    @ElementCollection
    @CollectionTable(name = "not_found_brands")
    protected List<String> notFoundBrands;
}
