package com.github.priceaggregator.mappers;

import com.github.priceaggregator.dto.MasterPriceRowDto;
import com.github.priceaggregator.entity.Goods;
import com.github.priceaggregator.entity.MasterBrand;
import com.github.priceaggregator.entity.MasterPrice;
import com.github.priceaggregator.entity.MasterPriceRow;
import org.mapstruct.Mapper;

import java.util.Map;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface MasterPriceRowMapper {

    default Optional<MasterPriceRow> masterDtoToMasterPriceRow(MasterPriceRowDto dto, Map<String, MasterBrand> brandMap, MasterPrice masterPrice) {
        MasterBrand masterBrand = brandMap.get(dto.getBrand());
        if (masterBrand == null) {
            return Optional.empty();
        }
        Goods goods = Goods.builder().name(dto.getName()).partNumber(dto.getPartNumber()).masterBrand(masterBrand).build();
        return Optional.of(MasterPriceRow.builder().masterPrice(masterPrice).goods(goods).amount(dto.getAmount()).price(dto.getPrice()).build());
    }


}
