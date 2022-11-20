package com.github.priceaggregator.service.implementations;

import com.github.priceaggregator.dao.abstracts.SupplierPriceDao;
import com.github.priceaggregator.dto.MasterPriceRowDto;
import com.github.priceaggregator.entity.Supplier;
import com.github.priceaggregator.entity.SupplierPrice;
import com.github.priceaggregator.exceptions.SupplierPriceNotFoundException;
import com.github.priceaggregator.service.abstracts.FileReader;
import com.github.priceaggregator.service.abstracts.MasterPriceService;
import com.github.priceaggregator.service.abstracts.SupplierPriceService;
import com.github.priceaggregator.service.priceReaders.PriceReaderFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SupplierPriceServiceImpl implements SupplierPriceService {

    private final SupplierPriceDao supplierPriceDao;

    private final MasterPriceService masterPriceService;

    private final PriceReaderFactory priceReaderFactory;

    public SupplierPriceServiceImpl(SupplierPriceDao supplierPriceDao, MasterPriceService masterPriceService, PriceReaderFactory priceReaderFactory) {
        this.supplierPriceDao = supplierPriceDao;
        this.masterPriceService = masterPriceService;
        this.priceReaderFactory = priceReaderFactory;
    }

    @Override
    public void save(SupplierPrice supplierPrice) {
        supplierPriceDao.save(supplierPrice);
    }

    @Override
    public SupplierPrice getSupplierPriceBySupplierAndLogo(Supplier supplier, String logo) {
        return supplierPriceDao.getSupplierPriceBySupplierAndLogo(supplier, logo).orElseThrow(SupplierPriceNotFoundException::new);
    }

    @Override
    public SupplierPrice getSupplierPriceById(Long id) {
        return supplierPriceDao.getReferenceById(id);
    }

    @Override
    public List<SupplierPrice> getAll() {
        return supplierPriceDao.findAll();
    }

    @Override
    @Transactional
    public void handleSupplierPrices() {
        List<SupplierPrice> prices = supplierPriceDao.getSupplierPriceByNeedToHandleIsTrue();
        prices.forEach(this::readSupplierPrice);

    }

    private void readSupplierPrice(SupplierPrice supplierPrice) {
        FileReader<MasterPriceRowDto> reader = priceReaderFactory.getPriceReader(supplierPrice.getFileSource(), supplierPrice.getReadProperties());
        masterPriceService.readSupplierPrice(supplierPrice, reader);
    }
}
