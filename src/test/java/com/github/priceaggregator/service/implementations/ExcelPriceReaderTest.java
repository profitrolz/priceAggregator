package com.github.priceaggregator.service.implementations;

import com.github.priceaggregator.PriceAggregatorApplicationTests;
import com.github.priceaggregator.entity.FileType;
import com.github.priceaggregator.entity.PriceFileSource;
import com.github.priceaggregator.entity.ReadProperties;
import com.github.priceaggregator.entity.SourceType;
import com.github.priceaggregator.service.abstracts.FileReader;
import com.github.priceaggregator.service.priceReaders.ExcelPriceReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;
import java.nio.file.Path;

class ExcelPriceReaderTest extends PriceAggregatorApplicationTests {

    @Test
    void readExcelFile_getOk() throws URISyntaxException {

//        PriceFileSource priceFileSource = PriceFileSource.builder()
//                .source(SourceType.FTP)
//                .fileName("pricetepark.xlsx")
//                .fileType(FileType.XLSX)
//                .build();

        ReadProperties readProperties = ReadProperties.builder()
                .partNumberColumn(0)
                .brandColumn(1)
                .nameColumn(2)
                .priceColumn(3)
                .amountColumn(4)
                .firstRow(1)
                .build();

        FileReader fileReader = new ExcelPriceReader(readProperties, Path.of(getClass().getClassLoader().getResource("excelPriceReader\\testSupplier\\pricetepark.xlsx").toURI()));
        Assertions.assertIterableEquals(ExcelPriceReaderUtil.getExpectedMasterPriceRows(), fileReader.readFile());


    }
}
