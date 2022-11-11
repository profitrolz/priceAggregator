package com.github.priceaggregator.service.components;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public class ExcelRowExtractor<T> {

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

        public Builder setDouble(BiConsumer<T, Double> setter, int columnNumber) {
            setters.add((row, obj) -> setter.accept(obj, getCellDouble(row, columnNumber)));
            return this;
        }

        public Builder setInteger(BiConsumer<T, Integer> setter, int columnNumber) {
            setters.add((row, obj) -> setter.accept(obj, getCellInteger(row, columnNumber)));
            return this;
        }


        public ExcelRowExtractor<T> build() {
            return ExcelRowExtractor.this;
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
                    return (int)row.getCell(num).getNumericCellValue();
                default:
                    throw new IllegalStateException();
            }
        }
    }
}
