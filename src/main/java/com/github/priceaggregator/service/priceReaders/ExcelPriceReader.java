package com.github.priceaggregator.service.priceReaders;

import com.github.priceaggregator.dto.MasterPriceRowDto;
import com.github.priceaggregator.entity.FileSource;
import com.github.priceaggregator.entity.ReadProperties;
import lombok.NoArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.function.BiConsumer;
import java.util.function.ObjDoubleConsumer;
import java.util.function.ObjIntConsumer;

@Component("excel")
public class ExcelPriceReader extends AbstractPriceReader {

    private ExcelRowExtractor<MasterPriceRowDto> excelRowExtractor;
    private int firstRow;

    public ExcelPriceReader(@Value("${file.upload-dir}") String localRootPath) {
        super(localRootPath);
    }

    @Override
    public List<MasterPriceRowDto> readFile() {
        List<MasterPriceRowDto> list = new ArrayList<>();
        try (FileInputStream file = new FileInputStream(filePath)) {
            Workbook workbook = new XSSFWorkbook(file);
            Sheet sheet = workbook.getSheetAt(0);

            for (int i = firstRow; i <= sheet.getLastRowNum(); i++) {
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

    @Override
    public void configure(ReadProperties readProperties, FileSource fileSource) {
        excelRowExtractor = ExcelRowExtractor.<MasterPriceRowDto>newExtractorBuilder()
                .setString(MasterPriceRowDto::setPartNumber, readProperties.getPartNumberColumn())
                .setString(MasterPriceRowDto::setName, readProperties.getNameColumn())
                .setString(MasterPriceRowDto::setBrand, readProperties.getBrandColumn())
                .setDouble(MasterPriceRowDto::setPrice, readProperties.getPriceColumn())
                .setInteger(MasterPriceRowDto::setAmount, readProperties.getAmountColumn())
                .build();

        firstRow = readProperties.getFirstRow();

        StringJoiner stringJoiner = new StringJoiner(File.separator);
        filePath = stringJoiner.add(localRootPath)
                .add(fileSource.getFilePath())
                .add(fileSource.getFileName())
                .toString();
    }

    private static class ExcelRowExtractor<T> {

        @FunctionalInterface
        private interface Setter<T> {
            void setValue(Row row, T value);
        }

        private final List<Setter<T>> setters = new ArrayList<>();

        public static <T> ExcelRowExtractor<T>.Builder newExtractorBuilder() {
            return new ExcelRowExtractor<T>().new Builder();
        }

        public T extract(T obj, Row row) {
            for (Setter<T> setter : setters) {
                setter.setValue(row, obj);
            }
            return obj;
        }

        public class Builder {

            public Builder setString(BiConsumer<T, String> setter, int columnNumber) {
                setters.add((row, obj) -> setter.accept(obj, getCellString(row, columnNumber)));
                return this;
            }

            public Builder setDouble(ObjDoubleConsumer<T> setter, int columnNumber) {
                setters.add((row, obj) -> setter.accept(obj, getCellDouble(row, columnNumber)));
                return this;
            }

            public Builder setInteger(ObjIntConsumer<T> setter, int columnNumber) {
                setters.add((row, obj) -> setter.accept(obj, getCellInteger(row, columnNumber)));
                return this;
            }


            public ExcelPriceReader.ExcelRowExtractor<T> build() {
                return ExcelPriceReader.ExcelRowExtractor.this;
            }

            private String getCellString(Row row, int num) {
                Cell cell = row.getCell(num);
                switch (cell.getCellType()) {
                    case STRING:
                        return row.getCell(num).getStringCellValue();
                    case NUMERIC:
                        return Double.toString(row.getCell(num).getNumericCellValue());
                    default:
                        throw new IllegalStateException();
                }
            }

            private double getCellDouble(Row row, int num) {
                Cell cell = row.getCell(num);
                switch (cell.getCellType()) {
                    case STRING:
                        return Double.parseDouble(row.getCell(num).getStringCellValue());
                    case NUMERIC:
                        return row.getCell(num).getNumericCellValue();
                    default:
                        throw new IllegalStateException();
                }
            }

            private int getCellInteger(Row row, int num) {
                Cell cell = row.getCell(num);
                switch (cell.getCellType()) {
                    case STRING:
                        return (int) Double.parseDouble(row.getCell(num).getStringCellValue());
                    case NUMERIC:
                        return (int) row.getCell(num).getNumericCellValue();
                    default:
                        throw new IllegalStateException();
                }
            }
        }
    }

}
