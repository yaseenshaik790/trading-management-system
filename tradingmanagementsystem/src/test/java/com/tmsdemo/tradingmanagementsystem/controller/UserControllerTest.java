package com.tmsdemo.tradingmanagementsystem.controller;

/**
 * UserControllerTest class is used to test taking the user and sending response
 * @author ShaikYaseen
 */
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import com.tmsdemo.tradingmanagementsystem.TestData;
import com.tmsdemo.tradingmanagementsystem.dto.StockPurchaseRequest;
import com.tmsdemo.tradingmanagementsystem.entity.Stock;
import com.tmsdemo.tradingmanagementsystem.entity.User;
import com.tmsdemo.tradingmanagementsystem.entity.UserShares;
import com.tmsdemo.tradingmanagementsystem.response.UserResponse;
import com.tmsdemo.tradingmanagementsystem.response.UserSharesResponse;
import com.tmsdemo.tradingmanagementsystem.service.UserService;

@SpringBootTest
public class UserControllerTest {

	@Autowired
	private UserController userController;

	@MockBean
	private UserService userService;

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
	public void getUserSharesTest() {
		List<UserShares> shares = new ArrayList<>();
		shares.add(userShares);
		shares.add(userShares1);
		UserSharesResponse userSharesResponse = new UserSharesResponse(shares, shares.size());
		when(userService.getUserShares(TestData.userId)).thenReturn(userSharesResponse);
		ResponseEntity<UserSharesResponse> responseEntity = userController.getUserShares(TestData.userId);
		assertThat(userSharesResponse.getShares()).isEqualTo(responseEntity.getBody().getShares());
		assertThat(userSharesResponse.getSharesListSize()).isEqualTo(responseEntity.getBody().getSharesListSize());

	}

	@Test
	public void purchaseStockTest() {
		StockPurchaseRequest purchaseRequest = new StockPurchaseRequest();
		purchaseRequest.setQuantity(2);
		purchaseRequest.setStockId(1l);
		UserResponse userResponse = new UserResponse("Stock purchased successfully ", 666);
		when(userService.purchaseStock(TestData.userId, purchaseRequest)).thenReturn(userResponse);
		ResponseEntity<UserResponse> responseEntity = userController.purchaseStock(TestData.userId, purchaseRequest);
		assertThat(userResponse.getMessage()).isEqualTo(responseEntity.getBody().getMessage());
		assertThat(userResponse.getStatusCode()).isEqualTo(responseEntity.getBody().getStatusCode());

	}

}
