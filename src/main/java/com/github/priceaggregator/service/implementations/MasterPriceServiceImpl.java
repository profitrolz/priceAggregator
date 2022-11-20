package com.github.priceaggregator.service.implementations;

import com.github.priceaggregator.dao.abstracts.MasterPriceDao;
import com.github.priceaggregator.dao.abstracts.SupplierPriceDao;
import com.github.priceaggregator.dto.MasterPriceRowDto;
import com.github.priceaggregator.entity.*;
import com.github.priceaggregator.service.abstracts.MasterPriceService;
import com.github.priceaggregator.service.abstracts.FileReader;
import com.github.priceaggregator.mappers.MasterPriceRowMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MasterPriceServiceImpl implements MasterPriceService {
    private final MasterPriceDao masterPriceDao;

    private final MasterPriceRowMapper mapper;

    private final SupplierPriceDao supplierPriceDao;

    @Value("file.upload-dir")
    private String filePath;

    public MasterPriceServiceImpl(MasterPriceDao masterPriceDao, MasterPriceRowMapper mapper, SupplierPriceDao supplierPriceDao) {
        this.masterPriceDao = masterPriceDao;
        this.mapper = mapper;
        this.supplierPriceDao = supplierPriceDao;
    }

    @Override
    public void saveMasterPrice(MasterPrice masterPrice) {
        masterPriceDao.save(masterPrice);
    }

    @Override
    @Transactional
    public MasterPrice readSupplierPrice(SupplierPrice supplierPrice, FileReader<MasterPriceRowDto> fileReader) {

        MasterPrice masterPrice = masterPriceDao.getMasterPriceBySupplierPrice(supplierPrice).orElseGet(() -> MasterPrice.builder().supplierPrice(supplierPrice).build());

        List<MasterPriceRowDto> masterPriceRows = fileReader.readFile();
        Map<String, MasterBrand> brandMap = supplierPrice.getSupplier().getBrandMap();
        List<String> notFoundBrands = masterPriceRows.stream()
                .map(MasterPriceRowDto::getBrand)
                .filter(brand -> !brandMap.containsKey(brand))
                .collect(Collectors.toList());
        masterPrice.setNotFoundBrands(notFoundBrands);

        List<MasterPriceRow> collect = masterPriceRows
                .stream()
                .map(s -> mapper.masterDtoToMasterPriceRow(s, brandMap, masterPrice))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());

        masterPrice.setRows(collect);
        masterPriceDao.save(masterPrice);
        return masterPrice;
    }

}
