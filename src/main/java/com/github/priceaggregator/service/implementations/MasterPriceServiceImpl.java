package com.github.priceaggregator.service.implementations;

import com.github.priceaggregator.dao.abstracts.MasterPriceDao;
import com.github.priceaggregator.dto.MasterPriceRowDto;
import com.github.priceaggregator.entity.*;
import com.github.priceaggregator.service.abstracts.MasterPriceService;
import com.github.priceaggregator.service.abstracts.FileReader;
import com.github.priceaggregator.service.abstracts.SupplierPriceService;
import com.github.priceaggregator.mappers.MasterPriceRowMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MasterPriceServiceImpl implements MasterPriceService {
    private final MasterPriceDao masterPriceDao;

    private final MasterPriceRowMapper mapper;

    @Value("file.upload-dir")
    private String filePath;

    public MasterPriceServiceImpl(MasterPriceDao masterPriceDao, MasterPriceRowMapper mapper) {
        this.masterPriceDao = masterPriceDao;
        this.mapper = mapper;
    }

    @Override
    public void saveMasterPrice(MasterPrice masterPrice) {
        masterPriceDao.save(masterPrice);
    }

    @Override
    @Transactional
    public MasterPrice readSupplierPrice(SupplierPrice supplierPrice, FileReader<MasterPriceRowDto> fileReader) {
        MasterPrice masterPrice = masterPriceDao.getMasterPriceBySupplierPrice(supplierPrice).orElseGet(() -> MasterPrice.builder().supplierPrice(supplierPrice).build());

        ReadProperties properties = supplierPrice.getReadProperties();
        Path pricePath = Path.of(filePath, properties.getFileName());

        List<MasterPriceRowDto> masterPriceRows = fileReader.readFile();
        Map<String, MasterBrand> brandMap = supplierPrice.getSupplier().getBrandMap();
        List<NotFoundBrands> notFoundBrands = masterPriceRows.stream()
                .filter(s -> !brandMap.containsKey(s.getBrand()))
                .map(s -> NotFoundBrands.builder().brandName(s.getBrand()).masterPrice(masterPrice).build())
                .collect(Collectors.toList());
        masterPrice.setNotFoundBrands(notFoundBrands);

        masterPrice.setRows(masterPriceRows
                .stream()
                .map(s -> mapper.masterDtoToMasterPriceRow(s, brandMap, masterPrice))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList()));
        masterPriceDao.save(masterPrice);
        return masterPrice;
    }

}
