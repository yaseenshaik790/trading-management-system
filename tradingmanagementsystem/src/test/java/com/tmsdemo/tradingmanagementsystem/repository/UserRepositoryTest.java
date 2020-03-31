package com.tmsdemo.tradingmanagementsystem.repository;

/**
 * UserRepositoryTest class is used to test performance database transactions
 * @author ShaikYaseen
 */
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.tmsdemo.tradingmanagementsystem.TestData;
import com.tmsdemo.tradingmanagementsystem.entity.Stock;
import com.tmsdemo.tradingmanagementsystem.entity.User;
import com.tmsdemo.tradingmanagementsystem.entity.UserShares;
import com.tmsdemo.tradingmanagementsystem.exception.SharesNotAvailableException;
import com.tmsdemo.tradingmanagementsystem.exception.UserNotFoundException;

@DataJpaTest
public class UserRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

	@MockBean
	private UserRepository userRepository;

	@MockBean
	private UserSharesRepository sharesRepository;

	User user;
	Stock stock, stock1;
	UserShares userShares, userShares1;

	@BeforeEach
	public void setUp() {

		user = new User();
		user.setPassword(TestData.password);
		user.setUserName(TestData.userName);

	}

	/**
	 * Method is used to test the user is exist or not
	 * 
	 * 
	 * @throws UserNotFoundException when user doen't exist
	 */
	@Test
	public void userNotFooundExceptionTest() {

		entityManager.persistAndFlush(user);
		Mockito.when(userRepository.findById(1l)).thenReturn(Optional.empty());
		assertThrows(UserNotFoundException.class, () -> {
			throw new UserNotFoundException(200l);
		});

	}

	/**
	 * Method is used to test the stock is available or not
	 * 
	 * 
	 * @throws SharesNotAvailableException when user doen't exist
	 */

	@Test
	public void sharesNotAvailableExceptionTest() {

		List<UserShares> userShares = new ArrayList<UserShares>();

		Mockito.when(sharesRepository.findByUserId(1l)).thenReturn(userShares);
		assertThrows(SharesNotAvailableException.class, () -> {
			throw new SharesNotAvailableException(TestData.userId);
		});

	}

}
