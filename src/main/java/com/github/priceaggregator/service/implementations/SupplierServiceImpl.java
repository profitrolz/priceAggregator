package com.github.priceaggregator.service.implementations;

import com.github.priceaggregator.dao.abstracts.SupplierDao;
import com.github.priceaggregator.entity.MasterBrand;
import com.github.priceaggregator.entity.Supplier;
import com.github.priceaggregator.exceptions.SupplierNotFoundException;
import com.github.priceaggregator.service.abstracts.SupplierService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SupplierServiceImpl implements SupplierService {
    private final SupplierDao supplierDao;

    public SupplierServiceImpl(SupplierDao supplierDao) {
        this.supplierDao = supplierDao;
    }

    @Override
    public void save(Supplier supplier) {
        supplierDao.save(supplier);
    }

    @Override
    public Supplier getSupplierByName(String name) {
        return supplierDao.getByName(name).orElseThrow(SupplierNotFoundException::new);
    }

    @Override
    @Transactional
    public void addBrandMapping(Long supplierId, String option, MasterBrand brand) {
        Supplier supplier = supplierDao.getReferenceById(supplierId);
        supplier.getBrandMap().put(option, brand);
        supplierDao.save(supplier);
    }
}
