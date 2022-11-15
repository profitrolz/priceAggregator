package com.github.priceaggregator.dao.abstracts;

import com.github.priceaggregator.entity.MasterPrice;
import com.github.priceaggregator.entity.Supplier;
import com.github.priceaggregator.entity.SupplierPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface MasterPriceDao extends JpaRepository<MasterPrice, Long> {

    Optional<MasterPrice> getMasterPriceBySupplierPrice(SupplierPrice supplierPrice);
}
