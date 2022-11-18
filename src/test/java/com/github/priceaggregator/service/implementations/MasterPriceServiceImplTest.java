package com.github.priceaggregator.service.implementations;

import com.github.priceaggregator.PriceAggregatorApplicationTests;
import com.github.priceaggregator.dto.MasterPriceRowDto;
import com.github.priceaggregator.service.abstracts.MasterPriceService;
import com.github.priceaggregator.service.abstracts.FileReader;
import com.github.priceaggregator.service.abstracts.SupplierPriceService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

class MasterPriceServiceImplTest extends PriceAggregatorApplicationTests {

    @Autowired
    private MasterPriceService service;

    @Autowired
    SupplierPriceService supplierPriceService;

    @Mock
    private FileReader<MasterPriceRowDto> fileReader;


    @Test
    @Sql("classpath:MasterPriceServiceImplTest/initTestData.sql")
    void saveMasterPrice() {
        Mockito.when(fileReader.readFile()).thenReturn(ExcelPriceReaderUtil.getExpectedMasterPriceRows());
        service.readSupplierPrice(supplierPriceService.getSupplierPriceById(1L), fileReader);
    }
}