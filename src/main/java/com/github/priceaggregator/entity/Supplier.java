package com.github.priceaggregator.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Map;

@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(unique = true)
    private String name;

    @ManyToMany
    @JoinTable(name = "supplier_brands_mapping",
    joinColumns = @JoinColumn(name = "supplier_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "masterBrand_id", referencedColumnName = "id"))
    @MapKeyColumn(name = "supplier_option")
    private Map<String, MasterBrand> brandMap;


}
