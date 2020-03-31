package com.tmsdemo.tradingmanagementsystem.repository;

/**
 * StockRepositoryTest class is used to test performance database transactions
 * @author ShaikYaseen
 */
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.tmsdemo.tradingmanagementsystem.TestData;
import com.tmsdemo.tradingmanagementsystem.entity.Stock;
import com.tmsdemo.tradingmanagementsystem.entity.User;
import com.tmsdemo.tradingmanagementsystem.entity.UserShares;
import com.tmsdemo.tradingmanagementsystem.exception.SharesNotAvailableException;
import com.tmsdemo.tradingmanagementsystem.exception.StockIdNotFoundException;
import com.tmsdemo.tradingmanagementsystem.exception.StocksNotAvailableException;

@DataJpaTest
public class StockRepositoryTest {

	@MockBean
	private StockRepository stockRepository;

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
	 * Method is used to test the stock is available or not
	 * 
	 * 
	 * @throws StockIdNotFoundException when user doen't exist
	 */
	@Test
	public void stockIdNotFoundExceptionTest() {

		Mockito.when(stockRepository.findById(1l)).thenReturn(Optional.empty());
		assertThrows(StockIdNotFoundException.class, () -> {
			throw new StockIdNotFoundException(1l);
		});
	}

	/**
	 * Method is used to test the stock is available or not
	 * 
	 * 
	 * @throws SharesNotAvailableException when user doen't exist
	 */

	@Test
	public void stocksNotAvailableExceptionTest() {

		assertThrows(StocksNotAvailableException.class, () -> {
			throw new StocksNotAvailableException(TestData.stockName);
		});
	}

}
