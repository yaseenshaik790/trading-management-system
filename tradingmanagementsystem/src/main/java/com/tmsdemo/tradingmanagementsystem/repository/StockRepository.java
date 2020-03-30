package com.tmsdemo.tradingmanagementsystem.repository;
/**
 * These class is used to perform database transactions
 * @author ShaikYaseen
 */
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tmsdemo.tradingmanagementsystem.entity.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

	Page<Stock> findByStockNameContains(String stockName, Pageable pageable);

}
