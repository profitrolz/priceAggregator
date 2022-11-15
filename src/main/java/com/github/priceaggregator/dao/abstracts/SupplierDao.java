package com.github.priceaggregator.dao.abstracts;

import com.github.priceaggregator.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SupplierDao extends JpaRepository<Supplier, Long> {

    Optional<Supplier> getByName(String name);
}
