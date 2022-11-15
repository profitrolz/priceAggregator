package com.github.priceaggregator.service.abstracts;

import com.github.priceaggregator.entity.MasterBrand;
import com.github.priceaggregator.entity.Supplier;

public interface SupplierService {
    void save(Supplier supplier);

    Supplier getSupplierByName(String name);

    void addBrandMapping(Long supplierId, String option, MasterBrand brand);

}
