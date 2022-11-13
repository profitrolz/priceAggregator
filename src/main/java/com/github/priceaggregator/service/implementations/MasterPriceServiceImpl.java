package com.github.priceaggregator.service.implementations;

import com.github.priceaggregator.dao.abstracts.MasterPriceDao;
import com.github.priceaggregator.dto.MasterPriceRowDto;
import com.github.priceaggregator.entity.MasterPrice;
import com.github.priceaggregator.entity.ReadProperties;
import com.github.priceaggregator.entity.Supplier;
import com.github.priceaggregator.entity.SupplierPrice;
import com.github.priceaggregator.service.abstracts.MasterPriceService;
import com.github.priceaggregator.service.abstracts.PriceReader;
import com.github.priceaggregator.service.priceReaders.PriceReaderFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;

@Service
public class MasterPriceServiceImpl implements MasterPriceService {
    private final MasterPriceDao masterPriceDao;

    @Value("file.upload-dir")
    private String filePath;

    public MasterPriceServiceImpl(MasterPriceDao masterPriceDao) {
        this.masterPriceDao = masterPriceDao;
    }

    @Override
    public void saveMasterPrice(MasterPrice masterPrice) {
        masterPriceDao.save(masterPrice);
    }

    @Override
    @Transactional
    public MasterPrice readSupplierPrice(SupplierPrice supplierPrice) {
        MasterPrice masterPrice = masterPriceDao.getMasterPriceBySupplierPrice(supplierPrice).orElseGet(() -> MasterPrice.builder().supplierPrice(supplierPrice).build());

        ReadProperties properties = supplierPrice.getReadProperties();
        Path pricePath = Path.of(filePath, properties.getFileName());

        PriceReader priceReader = PriceReaderFactory.getPriceReader(supplierPrice.getReadProperties(), pricePath);
        List<MasterPriceRowDto> masterPriceRows = priceReader.readPrice();
        Supplier supplier = supplierPrice.getSupplier();

        masterPrice.getRows().clear();
        return masterPrice;


    }
}
