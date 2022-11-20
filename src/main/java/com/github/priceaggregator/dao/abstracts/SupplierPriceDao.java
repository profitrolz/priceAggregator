package com.github.priceaggregator.dao.abstracts;

import com.github.priceaggregator.entity.Supplier;
import com.github.priceaggregator.entity.SupplierPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SupplierPriceDao extends JpaRepository<SupplierPrice, Long> {

    Optional<SupplierPrice> getSupplierPriceBySupplierAndLogo(Supplier supplier, String logo);

    List<SupplierPrice> getSupplierPriceByNeedToHandleIsTrue();
}
