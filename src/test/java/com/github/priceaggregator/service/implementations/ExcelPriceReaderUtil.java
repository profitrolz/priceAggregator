package com.github.priceaggregator.service.implementations;

import com.github.priceaggregator.dto.MasterPriceRowDto;
import com.github.priceaggregator.entity.MasterBrand;
import com.github.priceaggregator.entity.MasterPriceRow;

import java.util.List;

public final class ExcelPriceReaderUtil {
    private ExcelPriceReaderUtil() {
    }

    public static List<MasterPriceRowDto> getExpectedMasterPriceRows() {

        return List.of(new MasterPriceRowDto("T915","Фильтр топливный","ACDELCO",400d,7),
                new MasterPriceRowDto("TP864","Фильтр топливный","ACDELCO",800d,5),
                new MasterPriceRowDto("114R15","Рукав","AEROQUIP",3234.62,1),
                new MasterPriceRowDto("AS334","Фильтр","AGAMA",1906.08,1)
        );
    }

}
