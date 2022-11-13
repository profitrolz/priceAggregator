package com.github.priceaggregator.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Map;

@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Supplier {
    @Id
    private String name;

    @OneToMany
    @JoinTable(name = "supplier_brands_mapping",
    joinColumns = @JoinColumn(name = "supplier_name", referencedColumnName = "name"),
    inverseJoinColumns = @JoinColumn(name = "masterBrand_id", referencedColumnName = "id"))
    @MapKeyColumn(name = "supplier_option")
    private Map<String, MasterBrand> brandMap;


}
