package com.github.priceaggregator.service.implementations;

import com.github.priceaggregator.dao.abstracts.SupplierPriceDao;
import com.github.priceaggregator.entity.Supplier;
import com.github.priceaggregator.entity.SupplierPrice;
import com.github.priceaggregator.exceptions.SupplierPriceNotFoundException;
import com.github.priceaggregator.service.abstracts.SupplierPriceService;
import org.springframework.stereotype.Service;

@Service
public class SupplierPriceServiceImpl implements SupplierPriceService {

    private final SupplierPriceDao supplierPriceDao;

    public SupplierPriceServiceImpl(SupplierPriceDao supplierPriceDao) {
        this.supplierPriceDao = supplierPriceDao;
    }

    @Override
    public void save(SupplierPrice supplierPrice) {
        supplierPriceDao.save(supplierPrice);
    }

    @Override
    public SupplierPrice getSupplierPriceBySupplierAndLogo(Supplier supplier, String logo) {
        return supplierPriceDao.getSupplierPriceBySupplierAndLogo(supplier, logo).orElseThrow(SupplierPriceNotFoundException::new);
    }
}
