package com.github.priceaggregator.service.implementations;

import com.github.priceaggregator.PriceAggregatorApplicationTests;
import com.github.priceaggregator.entity.MasterBrand;
import com.github.priceaggregator.service.abstracts.SupplierPriceService;
import com.github.priceaggregator.service.abstracts.SupplierService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;

class SupplierServiceImplTest extends PriceAggregatorApplicationTests {

    @Autowired
    private SupplierService service;

    @Test
    @Sql("classpath:excelPriceReader/sql/SupplierServiceImplTest/initTestData.sql")
    void addBrandMapping() {
        MasterBrand masterBrand = MasterBrand.builder().id(2).name("Mazda").build();
        service.addBrandMapping(1L, "Mazda corp",masterBrand);
    }
}