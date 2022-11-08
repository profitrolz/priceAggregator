package com.github.priceaggregator.dao.abstracts;

import com.github.priceaggregator.entity.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodsDao extends JpaRepository<Goods, Long> {
}
