package com.github.priceaggregator.dao.abstracts;

import com.github.priceaggregator.entity.SupplierPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierPriceDao extends JpaRepository<SupplierPrice, Long> {
}
