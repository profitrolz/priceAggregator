package com.github.priceaggregator.service.implementations;

import com.github.priceaggregator.dao.abstracts.GoodsDao;
import com.github.priceaggregator.dto.MasterPriceRowDto;
import com.github.priceaggregator.entity.MasterPriceRow;
import com.github.priceaggregator.entity.ReadProperties;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.util.List;

@Component
public class ExcelPriceReader extends AbstractFilePriceReader{

    private Path filePath;

    private final GoodsDao goodsDao;

    public ExcelPriceReader(GoodsDao goodsDao) {
        this.goodsDao = goodsDao;
    }

    @Override
    public List<MasterPriceRowDto> readPrice() {
        return null;
    }

    @Override
    public void setReadProperties(ReadProperties readProperties) {
        this.readProperties = readProperties;
        this.filePath = Path.of("");
    }
}
