package com.github.priceaggregator.service.abstracts;

import com.github.priceaggregator.entity.MasterPrice;
import com.github.priceaggregator.entity.SupplierPrice;

public interface MasterPriceService {
    void saveMasterPrice(MasterPrice masterPrice);

    MasterPrice readSupplierPrice(SupplierPrice supplierPrice);
}
