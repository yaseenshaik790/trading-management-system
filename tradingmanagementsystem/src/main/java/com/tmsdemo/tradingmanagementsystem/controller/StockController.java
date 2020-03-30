package com.tmsdemo.tradingmanagementsystem.controller;

/**
 * StockController class is used to taking the request from user and sending response
 * @author ShaikYaseen
 */
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tmsdemo.tradingmanagementsystem.dto.StockDTO;
import com.tmsdemo.tradingmanagementsystem.exception.StockIdNotFoundException;
import com.tmsdemo.tradingmanagementsystem.exception.StocksNotAvailableException;
import com.tmsdemo.tradingmanagementsystem.response.StockResponse;
import com.tmsdemo.tradingmanagementsystem.service.StockService;

@RestController
@RequestMapping("/stocks")
public class StockController {

	private static Logger logger = LoggerFactory.getLogger(StockController.class);

	@Autowired
	private StockService stockService;

	/**
	 * getStocksByStockName Method is used to fetch the stock
	 * 
	 * @param pageNumber for the pagination purpose
	 * @param pageSize   for the pagination purpose
	 * @throws StocksNotAvailableException when stocks are not available
	 */
	@GetMapping("")
	public ResponseEntity<StockResponse> getStocksByStockName(@RequestParam Integer pageNumber,
			@RequestParam Integer pageSize, @RequestParam String stockName) {
		logger.info("enters into getStockByStockName method to fetch Stock details");

		return new ResponseEntity<>(stockService.getStocksByStockName(pageNumber, pageSize, stockName.trim()),
				HttpStatus.FOUND);
	}

	/**
	 * Method is used to fetch the stock details
	 * 
	 * @param stockId to fetch the stock
	 * @throws StockIdNotFoundException when user enters wrong stok id
	 */
	@GetMapping("/{stockId}")
	public ResponseEntity<StockDTO> getStockByStockId(@PathVariable Long stockId) {
		logger.info("enters into getStockByStockId method to fetch Stock details");

		return new ResponseEntity<>(stockService.getStockByStockId(stockId), HttpStatus.FOUND);
	}

}
