package com.github.priceaggregator.service.implementations;

import com.github.priceaggregator.PriceAggregatorApplicationTests;
import com.github.priceaggregator.entity.SupplierPrice;
import com.github.priceaggregator.service.abstracts.MasterPriceService;
import com.github.priceaggregator.service.abstracts.PriceReader;
import com.github.priceaggregator.service.abstracts.SupplierPriceService;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;

class MasterPriceServiceImplTest extends PriceAggregatorApplicationTests {

    @Autowired
    private MasterPriceService service;

    @Autowired
    SupplierPriceService supplierPriceService;

    @Mock
    private PriceReader priceReader;


    @Test
    @Sql("classpath:MasterPriceServiceImplTest/initTestData.sql")
    void saveMasterPrice() {
        Mockito.when(priceReader.readPrice()).thenReturn(ExcelPriceReaderUtil.getExpectedMasterPriceRows());
        service.readSupplierPrice(supplierPriceService.getSupplierPriceById(1L), priceReader);
    }
}