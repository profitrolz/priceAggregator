package com.github.priceaggregator.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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

    @OneToMany(mappedBy = "masterPrice")
    protected List<MasterPriceRow> rows;

    @OneToOne
    protected SupplierPrice supplierPrice;

}
