package com.github.priceaggregator.service.abstracts;


import com.github.priceaggregator.dto.MasterPriceRowDto;
import com.github.priceaggregator.entity.Supplier;
import com.github.priceaggregator.entity.SupplierPrice;

import java.util.List;

public interface SupplierPriceService {

    void save(SupplierPrice supplierPrice);

    SupplierPrice getSupplierPriceBySupplierAndLogo(Supplier supplier, String logo);

    SupplierPrice getSupplierPriceById(Long id);

    List<SupplierPrice> getAll();

    void handleSupplierPrices();

}
