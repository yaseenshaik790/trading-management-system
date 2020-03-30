package com.tmsdemo.tradingmanagementsystem.service;

/**
 * UserServiceImpl is used to write business logic
 * @author YaseenShaik
 */
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private StockRepository stockRepository;

	@Autowired
	private UserSharesRepository userSharesRepository;
	private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	/**
	 * Method is used to purchase the stock
	 * 
	 * @param stockId to fetch the stock
	 * @throws UserNotFoundException    when user doesn't exist
	 * @throws StockIdNotFoundException when user enters wrong stock id
	 */
	@Transactional
	public UserResponse purchaseStock(Long userId, StockPurchaseRequest purchaseRequest) {
		logger.info("purchase the stock ");
		Optional<User> user = userRepository.findById(userId);
		if (!user.isPresent()) {
			logger.warn("user not found");
			throw new UserNotFoundException(userId);
		}

		Optional<Stock> stock = stockRepository.findById(purchaseRequest.getStockId());
		if (!stock.isPresent()) {
			logger.warn("stocks not found");
			throw new StockIdNotFoundException(purchaseRequest.getStockId());
		}

		Double stockPrice = stock.get().getStockUnitPrice() * purchaseRequest.getQuantity();

		UserShares userShares = new UserShares();
		userShares.setPurchaseDate(LocalDate.now());
		userShares.setStockId(stock.get().getStockId());
		userShares.setStockPrice(stockPrice);
		userShares.setUserId(user.get().getUserId());
		userShares.setStockQuantity(purchaseRequest.getQuantity());

		userSharesRepository.save(userShares);
		logger.info(" stock purchased successfully");
		return new UserResponse("Stock purchased successfully ", 666);
	}

	/**
	 * Method is used to fetch the share details
	 * 
	 * @param stockId to fetch the stock
	 * @throws SharesNotAvailableException when user enters wrong stok id
	 */
	@Transactional
	public UserSharesResponse getUserShares(Long userId) {

		logger.info(" fetching the user shares by using user id");
		Optional<User> user = userRepository.findById(userId);
		if (!user.isPresent()) {
			logger.warn(" suser not found");
			throw new UserNotFoundException(userId);
		}

		List<UserShares> userShares = userSharesRepository.findByUserId(userId);
		if (CollectionUtils.isEmpty(userShares)) {
			logger.warn(" stock not available");
			throw new SharesNotAvailableException(userId);
		}
		return new UserSharesResponse(userShares, userShares.size());
	}

}
