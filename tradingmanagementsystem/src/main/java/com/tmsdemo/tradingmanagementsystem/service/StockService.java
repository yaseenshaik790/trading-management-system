package com.tmsdemo.tradingmanagementsystem.service;
/**
 * interface for StockService
 * @author ShaikYaseen
 */
import com.tmsdemo.tradingmanagementsystem.dto.StockDTO;
import com.tmsdemo.tradingmanagementsystem.response.StockResponse;

public interface StockService {

	StockResponse getStocksByStockName(Integer pageNumber, Integer pageSize, String stockName);

	StockDTO getStockByStockId(Long stockId);

}
