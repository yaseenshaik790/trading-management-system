package com.tmsdemo.tradingmanagementsystem.controller;

/**
 * StockControllerTest class is used to test taking the user and sending response
 * @author ShaikYaseen
 */
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import com.tmsdemo.tradingmanagementsystem.TestData;
import com.tmsdemo.tradingmanagementsystem.dto.StockDTO;
import com.tmsdemo.tradingmanagementsystem.entity.Stock;
import com.tmsdemo.tradingmanagementsystem.entity.User;
import com.tmsdemo.tradingmanagementsystem.entity.UserShares;
import com.tmsdemo.tradingmanagementsystem.response.StockResponse;
import com.tmsdemo.tradingmanagementsystem.service.StockService;

@SpringBootTest
public class StockControllerTest {

	@Autowired
	private StockController stockController;

	@MockBean
	private StockService stockService;

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

	@Test
	public void getStocksByStockNameTest() {

		List<Stock> stocks = new ArrayList<Stock>();
		stocks.add(stock);
		StockResponse StockResponse = new StockResponse(stocks, stocks.size());
		when(stockService.getStocksByStockName(0, 1, "SBI")).thenReturn(StockResponse);
		ResponseEntity<StockResponse> responseEntity = stockController.getStocksByStockName(0, 1, "SBI");
		assertThat(StockResponse.getStocks()).isEqualTo(responseEntity.getBody().getStocks());
		assertThat(StockResponse.getStockListSize()).isEqualTo(responseEntity.getBody().getStockListSize());

	}

	@Test
	public void getStockByStockIdTest() {

		StockDTO stockDTO = new StockDTO();
		BeanUtils.copyProperties(stock, stockDTO);

		when(stockService.getStockByStockId(TestData.stockId)).thenReturn(stockDTO);
		ResponseEntity<StockDTO> responseEntity = stockController.getStockByStockId(TestData.stockId);
		assertThat(stockDTO).isEqualTo(responseEntity.getBody());
	}
}
