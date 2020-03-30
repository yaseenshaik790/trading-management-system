package com.tmsdemo.tradingmanagementsystem.service;

/**
 * UserServiceTest is used to test write business logic
 * @author YaseenShaik
 */
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.tmsdemo.tradingmanagementsystem.TestData;
import com.tmsdemo.tradingmanagementsystem.dto.StockPurchaseRequest;
import com.tmsdemo.tradingmanagementsystem.entity.Stock;
import com.tmsdemo.tradingmanagementsystem.entity.User;
import com.tmsdemo.tradingmanagementsystem.entity.UserShares;
import com.tmsdemo.tradingmanagementsystem.exception.SharesNotAvailableException;
import com.tmsdemo.tradingmanagementsystem.exception.StockIdNotFoundException;
import com.tmsdemo.tradingmanagementsystem.exception.UserNotFoundException;
import com.tmsdemo.tradingmanagementsystem.repository.StockRepository;
import com.tmsdemo.tradingmanagementsystem.repository.UserRepository;
import com.tmsdemo.tradingmanagementsystem.repository.UserSharesRepository;
import com.tmsdemo.tradingmanagementsystem.response.UserResponse;
import com.tmsdemo.tradingmanagementsystem.response.UserSharesResponse;

@SpringBootTest
public class UserServiceTest {

	@MockBean
	private UserRepository userRepository;

	@Autowired
	private UserService userService;

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

	@Test
	public void purchaseStockTestPositive() {

		when(userRepository.findById(TestData.userId)).thenReturn(Optional.of(user));
		when(stockRepository.findById(TestData.stockId)).thenReturn(Optional.of(stock));
		StockPurchaseRequest purchaseRequest = new StockPurchaseRequest();
		purchaseRequest.setQuantity(2);
		purchaseRequest.setStockId(TestData.stockId);
		Double stockPrice = stock.getStockUnitPrice() * purchaseRequest.getQuantity();
		UserShares userShares = new UserShares();
		userShares.setPurchaseDate(LocalDate.now());
		userShares.setStockId(stock.getStockId());
		userShares.setStockPrice(stockPrice);
		userShares.setUserId(user.getUserId());
		userShares.setStockQuantity(purchaseRequest.getQuantity());
		userSharesRepository.save(userShares);
		UserResponse purchaseStock = new UserResponse("Stock purchased successfully ", 666);
		UserResponse userResponse = userService.purchaseStock(TestData.userId, purchaseRequest);
		assertThat(purchaseStock.getMessage()).isEqualTo(userResponse.getMessage());
		assertThat(purchaseStock.getStatusCode()).isEqualTo(userResponse.getStatusCode());

	}

	@Test
	public void purchaseStockthrowsUserNotFoundExceptionTest() {
		when(userRepository.findById(TestData.userId)).thenReturn(Optional.empty());
		StockPurchaseRequest purchaseRequest = new StockPurchaseRequest();
		purchaseRequest.setQuantity(2);
		purchaseRequest.setStockId(TestData.stockId);
		assertThrows(UserNotFoundException.class, () -> {
			userService.purchaseStock(TestData.userId, purchaseRequest);
		});

	}

	@Test
	public void purchaseStockthrowsStockIdNotFoundExceptionTest() {
		when(userRepository.findById(TestData.userId)).thenReturn(Optional.of(user));
		when(stockRepository.findById(TestData.stockId)).thenReturn(Optional.empty());
		StockPurchaseRequest purchaseRequest = new StockPurchaseRequest();
		purchaseRequest.setQuantity(2);
		purchaseRequest.setStockId(TestData.stockId);
		assertThrows(StockIdNotFoundException.class, () -> {
			userService.purchaseStock(TestData.userId, purchaseRequest);
		});

	}

	@Test
	public void getUserSharesTestPositive() {
		when(userRepository.findById(TestData.userId)).thenReturn(Optional.of(user));
		List<UserShares> shares = new ArrayList<>();
		shares.add(userShares);
		when(userSharesRepository.findByUserId(TestData.userId)).thenReturn(shares);
		UserSharesResponse userSharesResponse = new UserSharesResponse(shares, shares.size());
		UserSharesResponse userShares = userService.getUserShares(TestData.userId);
		assertThat(userSharesResponse.getShares()).isEqualTo(userShares.getShares());
		assertThat(userSharesResponse.getSharesListSize()).isEqualTo(userShares.getSharesListSize());
	}

	@Test
	public void getUserSharesThrowsUserNotFoundExceptionTest() {
		when(userRepository.findById(TestData.userId)).thenReturn(Optional.empty());
		assertThrows(UserNotFoundException.class, () -> {
			userService.getUserShares(TestData.userId);
		});
	}

	@Test
	public void getUserSharesThrowsSharesNotAvailableExceptionTest() {
		when(userRepository.findById(TestData.userId)).thenReturn(Optional.of(user));
		List<UserShares> shares = new ArrayList<>();
		when(userSharesRepository.findByUserId(TestData.userId)).thenReturn(shares);
		assertThrows(SharesNotAvailableException.class, () -> {
			userService.getUserShares(TestData.userId);
		});
	}
	
	

}
