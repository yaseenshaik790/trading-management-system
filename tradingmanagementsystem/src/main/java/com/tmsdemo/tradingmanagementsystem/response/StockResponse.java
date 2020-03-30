package com.tmsdemo.tradingmanagementsystem.response;

/**
 * StockResponse class is used to send List of stocks
 * @author ShaikYaseen
 */
import java.util.List;

import com.tmsdemo.tradingmanagementsystem.entity.Stock;

import lombok.Getter;

@Getter
public class StockResponse {

	private List<Stock> stocks;

	private Integer stockListSize;

	public StockResponse(List<Stock> stocks, Integer stockListSize) {
		super();
		this.stocks = stocks;
		this.stockListSize = stockListSize;
	}

}
