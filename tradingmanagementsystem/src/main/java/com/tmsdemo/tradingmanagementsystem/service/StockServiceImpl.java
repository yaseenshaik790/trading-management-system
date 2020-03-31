package com.tmsdemo.tradingmanagementsystem.service;

/**
 * StockServiceImpl is used to write business logic
 * @author YaseenShaik
 */
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.tmsdemo.tradingmanagementsystem.dto.StockDTO;
import com.tmsdemo.tradingmanagementsystem.entity.Stock;
import com.tmsdemo.tradingmanagementsystem.exception.StockIdNotFoundException;
import com.tmsdemo.tradingmanagementsystem.exception.StocksNotAvailableException;
import com.tmsdemo.tradingmanagementsystem.repository.StockRepository;
import com.tmsdemo.tradingmanagementsystem.response.StockResponse;

@Service
public class StockServiceImpl implements StockService {

	@Autowired
	private StockRepository stockRepository;

	private static Logger logger = LoggerFactory.getLogger(StockServiceImpl.class);

	/**
	 *  Method is used to fetch the stock
	 * 
	 * @param pageNumber to give page number
	 * @param pageSize   to give page size
	 * @throws StocksNotAvailableException when stocks are not available
	 */
	public StockResponse getStocksByStockName(Integer pageNumber, Integer pageSize, String stockName) {
		logger.info("seaching the stock by using product name");
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<Stock> stocks = stockRepository.findByStockNameContains(stockName, pageable);

		if (CollectionUtils.isEmpty(stocks.getContent())) {
			logger.warn("stock are not available");
			throw new StocksNotAvailableException(stockName);
		}

		return new StockResponse(stocks.getContent(), stocks.getContent().size());
	}

	/**
	 * Method is used to fetch the stock details
	 * 
	 * @param stockId to fetch the stock
	 * @throws StockIdNotFoundException when user enters wrong stok id
	 */
	public StockDTO getStockByStockId(Long stockId) {
		logger.info("seaching the stock by using stockId");
		Optional<Stock> stock = stockRepository.findById(stockId);
		if (!stock.isPresent()) {
			logger.warn("seaching the stock is not available ");
			throw new StockIdNotFoundException(stockId);
		}
		StockDTO stockDTO = new StockDTO();
		BeanUtils.copyProperties(stock.get(), stockDTO);

		return stockDTO;
	}

}
