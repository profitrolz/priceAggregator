package com.github.priceaggregator.service.abstracts;


import com.github.priceaggregator.entity.Supplier;
import com.github.priceaggregator.entity.SupplierPrice;

public interface SupplierPriceService {

    void save(SupplierPrice supplierPrice);

    SupplierPrice getSupplierPriceBySupplierAndLogo(Supplier supplier, String logo);

}
