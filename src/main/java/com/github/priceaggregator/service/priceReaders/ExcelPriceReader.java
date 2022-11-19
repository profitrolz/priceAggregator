package com.github.priceaggregator.service.priceReaders;

import com.github.priceaggregator.dto.MasterPriceRowDto;
import com.github.priceaggregator.entity.ReadProperties;
import com.github.priceaggregator.service.components.ExcelRowExtractor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.context.ApplicationContext;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ExcelPriceReader extends AbstractPriceReader {

    public ExcelPriceReader(ReadProperties readProperties, Path filePath) {
        super(readProperties, filePath);
    }

    @Override
    public List<MasterPriceRowDto> readFile() {
        List<MasterPriceRowDto> list = new ArrayList<>();
        try (FileInputStream file = new FileInputStream(filePath.toFile())) {
            Workbook workbook = new XSSFWorkbook(file);
            Sheet sheet = workbook.getSheetAt(0);
            ExcelRowExtractor<MasterPriceRowDto> excelRowExtractor = ExcelRowExtractor.<MasterPriceRowDto>newExtractorBuilder()
                    .setString(MasterPriceRowDto::setPartNumber, readProperties.getPartNumberColumn())
                    .setString(MasterPriceRowDto::setName, readProperties.getNameColumn())
                    .setString(MasterPriceRowDto::setBrand, readProperties.getBrandColumn())
                    .setDouble(MasterPriceRowDto::setPrice, readProperties.getPriceColumn())
                    .setInteger(MasterPriceRowDto::setAmount, readProperties.getAmountColumn())
                    .build();

            for (int i = readProperties.getFirstRow(); i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                list.add(excelRowExtractor.extract(new MasterPriceRowDto(), row));
            }

        } catch (FileNotFoundException e) {
            //TODO log
        } catch (IOException e) {
            //TODO log
        }


        return list;
    }
}
