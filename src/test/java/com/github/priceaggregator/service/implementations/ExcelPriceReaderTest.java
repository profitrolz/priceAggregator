package com.github.priceaggregator.service.implementations;

import com.github.priceaggregator.PriceAggregatorApplicationTests;
import com.github.priceaggregator.dao.abstracts.GoodsDao;
import com.github.priceaggregator.entity.MasterPriceRow;
import com.github.priceaggregator.entity.ReadProperties;
import com.github.priceaggregator.service.abstracts.PriceReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

class ExcelPriceReaderTest extends PriceAggregatorApplicationTests {

    @Autowired
    protected GoodsDao goodsDao;

    @Test
    void readExcelFile_getOk() {
        ReadProperties readProperties = ReadProperties.builder()
                .fileName("pricetepark.xlsx")
                .source("//")
                .fileType(".xlsx")
                .partNumberColumn(1)
                .brandColumn(2)
                .nameColumn(3)
                .priceColumn(4)
                .amountColumn(5)
                .build();

        PriceReader priceReader = new ExcelPriceReader(goodsDao);
        priceReader.setReadProperties(readProperties);
        Assertions.assertIterableEquals(ExcelPriceReaderUtil.getExpectedMasterPriceRows(), priceReader.readPrice());


    }
}
