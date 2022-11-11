package com.github.priceaggregator.service.implementations;

import com.github.priceaggregator.dto.MasterPriceRowDto;
import com.github.priceaggregator.entity.ReadProperties;
import com.github.priceaggregator.service.components.ExcelRowExtractor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ExcelPriceReader extends AbstractFilePriceReader {

    private final Path filePath;

    public ExcelPriceReader(Path filePath, ReadProperties readProperties) {
        this.filePath = filePath;
        this.readProperties = readProperties;
    }

    @Override
    public List<MasterPriceRowDto> readPrice() {
        List<MasterPriceRowDto> list = new ArrayList<>();
        try (FileInputStream file = new FileInputStream(filePath.toFile())) {
            Workbook workbook = new XSSFWorkbook(file);
            Sheet sheet = workbook.getSheetAt(0);
            for (int i = readProperties.getFirstRow(); i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                ExcelRowExtractor<MasterPriceRowDto> excelRowExtractor = ExcelRowExtractor.<MasterPriceRowDto>newExtractorBuilder()
                        .setString(MasterPriceRowDto::setPartNumber, readProperties.getPartNumberColumn())
                        .setString(MasterPriceRowDto::setName, readProperties.getNameColumn())
                        .setString(MasterPriceRowDto::setBrand, readProperties.getBrandColumn())
                        .setDouble(MasterPriceRowDto::setPrice, readProperties.getPriceColumn())
                        .setInteger(MasterPriceRowDto::setAmount, readProperties.getAmountColumn())
                        .build();
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
