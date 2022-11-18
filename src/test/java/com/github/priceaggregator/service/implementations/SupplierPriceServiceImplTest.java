package com.github.priceaggregator.service.implementations;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.github.database.rider.junit5.api.DBRider;
import com.github.priceaggregator.PriceAggregatorApplicationTests;
import com.github.priceaggregator.dto.MasterPriceRowDto;
import com.github.priceaggregator.service.abstracts.MasterPriceService;
import com.github.priceaggregator.service.abstracts.FileReader;
import com.github.priceaggregator.service.abstracts.SupplierPriceService;
import com.github.priceaggregator.service.priceReaders.ExcelPriceReader;
import com.github.priceaggregator.service.priceReaders.PriceReaderFactory;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;

@DBRider
class SupplierPriceServiceImplTest extends PriceAggregatorApplicationTests {

    @Autowired
    private MasterPriceService service;

    @Autowired
    SupplierPriceService supplierPriceService;

    @MockBean
    private PriceReaderFactory priceReaderFactory;


    @Test
    @Sql("classpath:datasets/MasterPriceServiceImplTest/initTestData.sql")
    @DataSet(transactional = true)
    @ExpectedDataSet(value = "datasets/MasterPriceServiceImplTest/expected/masterPriceRows.xml", ignoreCols = {"id"})
    void saveMasterPrice() {

        FileReader<MasterPriceRowDto> fileReader = Mockito.mock(ExcelPriceReader.class);
        Mockito.when(fileReader.readFile()).thenReturn(ExcelPriceReaderUtil.getMasterPriceRows_saveMasterPrice());
        Mockito.when(priceReaderFactory.getPriceReader(Mockito.any(), Mockito.any())).thenReturn(fileReader);

        supplierPriceService.handleSupplierPrices();
    }

}