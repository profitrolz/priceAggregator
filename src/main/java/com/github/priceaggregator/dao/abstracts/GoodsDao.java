package com.github.priceaggregator.dao.abstracts;

import com.github.priceaggregator.entity.Goods;
import com.github.priceaggregator.entity.MasterBrand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GoodsDao extends JpaRepository<Goods, Long> {

    Optional<Goods> getByMasterBrandAndPartNumber(MasterBrand masterBrand, String partNumber);
}
