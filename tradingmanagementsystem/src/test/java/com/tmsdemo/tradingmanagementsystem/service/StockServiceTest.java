package com.tmsdemo.tradingmanagementsystem.service;

/**
 * StockServiceTest is used to test write business logic
 * @author YaseenShaik
 */
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.tmsdemo.tradingmanagementsystem.TestData;
import com.tmsdemo.tradingmanagementsystem.dto.StockDTO;
import com.tmsdemo.tradingmanagementsystem.entity.Stock;
import com.tmsdemo.tradingmanagementsystem.entity.User;
import com.tmsdemo.tradingmanagementsystem.entity.UserShares;
import com.tmsdemo.tradingmanagementsystem.exception.StockIdNotFoundException;
import com.tmsdemo.tradingmanagementsystem.exception.StocksNotAvailableException;
import com.tmsdemo.tradingmanagementsystem.repository.StockRepository;
import com.tmsdemo.tradingmanagementsystem.repository.UserRepository;
import com.tmsdemo.tradingmanagementsystem.repository.UserSharesRepository;
import com.tmsdemo.tradingmanagementsystem.response.StockResponse;

@SpringBootTest
public class StockServiceTest {

	@MockBean
	private UserRepository userRepository;

	@Autowired
	private StockService stockService;

	@MockBean
	private StockRepository stockRepository;

	@MockBean
	private UserSharesRepository userSharesRepository;

	User user;
	Stock stock, stock1;
	UserShares userShares, userShares1;

	@BeforeEach
	public void setUp() {

		user = new User();
		user.setPassword(TestData.password);
		user.setUserName(TestData.userName);

		stock = new Stock();
		stock.setStockAvailable(TestData.stcokAvailable);
		stock.setStockDate(TestData.stcokDate);
		stock.setStockId(TestData.stockId);
		stock.setStockName(TestData.stockName);
		stock.setStockUnitPrice(TestData.stcokPrice);
		stock1 = new Stock();
		stock1.setStockAvailable(TestData.stcokAvailable1);
		stock1.setStockDate(TestData.stcokDate1);
		stock1.setStockId(TestData.stockId1);
		stock1.setStockName(TestData.stockName1);
		stock1.setStockUnitPrice(TestData.stcokPrice);

		userShares = new UserShares();
		userShares.setPurchaseDate(TestData.purchaseDate);
		userShares.setStockId(TestData.stockId);
		userShares.setStockPrice(TestData.stcokPrice);
		userShares.setStockQuantity(TestData.stcokQuantity);
		userShares.setUserId(TestData.userId);

		userShares1 = new UserShares();
		userShares1.setPurchaseDate(TestData.purchaseDate1);
		userShares1.setStockId(TestData.stockId1);
		userShares1.setStockPrice(TestData.stcokPrice1);
		userShares1.setStockQuantity(TestData.stcokQuantity1);
		userShares1.setUserId(TestData.userId);

	}

	/**
	 * Method is used to test the fetch the stock
	 * 
	 * @param pageNumber to give page number
	 * @param pageSize   to give page size
	 * @throws StocksNotAvailableException when stocks are not available
	 */
	@Test
	public void getStocksByStockNameTest() {

		List<Stock> stocks = new ArrayList<Stock>();
		stocks.add(stock);

		Pageable pageable = PageRequest.of(0, 1);
		Page<Stock> stockList = new PageImpl<Stock>(stocks);

		when(stockRepository.findByStockNameContains(TestData.stockName, pageable)).thenReturn(stockList);
		StockResponse stockResponse = new StockResponse(stocks, stocks.size());
		assertThat(stockResponse.getStocks()).isEqualTo(stocks);
		assertThat(stockResponse.getStockListSize()).isEqualTo(stocks.size());

	}

	/**
	 * Method is used to test throwing exception when stock is not availed
	 * 
	 * @param pageNumber to give page number
	 * @param pageSize   to give page size
	 * @throws StocksNotAvailableException when stocks are not available
	 */
	@Test
	public void getStocksByStockNameStocksNotAvailableExceptionTest() {
		List<Stock> stocks = new ArrayList<Stock>();
		Pageable pageable = PageRequest.of(0, 1);
		Page<Stock> stockList = new PageImpl<Stock>(stocks);

		when(stockRepository.findByStockNameContains(TestData.stockName, pageable)).thenReturn(stockList);

		assertThrows(StocksNotAvailableException.class, () -> {
			stockService.getStocksByStockName(0, 1, TestData.stockName);
		});

	}

	/**
	 * Method is used to test fetch the stock details
	 * 
	 * @param stockId to fetch the stock
	 * @throws StockIdNotFoundException when user enters wrong stock id
	 */
	@Test
	public void getStockByStockIdTest() {

		when(stockRepository.findById(TestData.stockId)).thenReturn(Optional.of(stock));
		StockDTO stockDTO = new StockDTO();
		BeanUtils.copyProperties(stock, stockDTO);
		StockDTO stockResponse = stockService.getStockByStockId(TestData.stockId);
		assertThat(stockDTO).isEqualTo(stockResponse);

	}

	/**
	 * Method is used to test throws exception when Stocks are not avaialable
	 * 
	 * @param stockId to fetch the stock
	 * @throws StockIdNotFoundException when user enters wrong stock id
	 */
	@Test
	public void getStockByStockIdThrowsStockIdNotFoundExceptionTest() {

		when(stockRepository.findById(TestData.stockId)).thenReturn(Optional.empty());
		assertThrows(StockIdNotFoundException.class, () -> {

			stockService.getStockByStockId(TestData.stockId);
		});
	}

}
