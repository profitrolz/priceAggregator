package com.github.priceaggregator.dao.abstracts;

import com.github.priceaggregator.entity.MasterBrand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterBrandDao extends JpaRepository<MasterBrand, Long> {
}
