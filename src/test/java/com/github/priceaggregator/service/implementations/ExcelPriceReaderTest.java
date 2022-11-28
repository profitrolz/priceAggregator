package com.github.priceaggregator.service.implementations;

import com.github.priceaggregator.PriceAggregatorApplicationTests;
import com.github.priceaggregator.dto.MasterPriceRowDto;
import com.github.priceaggregator.entity.FileType;
import com.github.priceaggregator.entity.FileSource;
import com.github.priceaggregator.entity.ReadProperties;
import com.github.priceaggregator.service.abstracts.FileReader;
import com.github.priceaggregator.service.priceReaders.PriceReaderFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URISyntaxException;

class ExcelPriceReaderTest extends PriceAggregatorApplicationTests {

    @Autowired
    private PriceReaderFactory priceReaderFactory;

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


        FileSource fileSource = FileSource.builder()
                .filePath("excelPriceReader/testSupplier")
                .fileName("pricetepark.xlsx")
                .fileType(FileType.XLSX)
                .build();

        FileReader<MasterPriceRowDto> fileReader = priceReaderFactory.getPriceReader(fileSource, readProperties);
        Assertions.assertIterableEquals(ExcelPriceReaderUtil.getExpectedMasterPriceRows(), fileReader.readFile());


    }
}
