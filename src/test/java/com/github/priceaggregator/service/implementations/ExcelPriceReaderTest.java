package com.github.priceaggregator.service.implementations;

import com.github.priceaggregator.PriceAggregatorApplicationTests;
import com.github.priceaggregator.dao.abstracts.GoodsDao;
import com.github.priceaggregator.entity.MasterPriceRow;
import com.github.priceaggregator.entity.ReadProperties;
import com.github.priceaggregator.service.abstracts.PriceReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.util.List;

class ExcelPriceReaderTest extends PriceAggregatorApplicationTests {

    @Autowired
    protected GoodsDao goodsDao;

    @Test
    void readExcelFile_getOk() throws URISyntaxException {
        ReadProperties readProperties = ReadProperties.builder()
                .fileName("pricetepark.xlsx")
                .source("//")
                .fileType(".xlsx")
                .partNumberColumn(0)
                .brandColumn(1)
                .nameColumn(2)
                .priceColumn(3)
                .amountColumn(4)
                .firstRow(1)
                .build();

        PriceReader priceReader = new ExcelPriceReader(Path.of(getClass().getClassLoader().getResource("excelPriceReader\\testSupplier\\pricetepark.xlsx").toURI()), readProperties);
        Assertions.assertIterableEquals(ExcelPriceReaderUtil.getExpectedMasterPriceRows(), priceReader.readPrice());


    }
}
