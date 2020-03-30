package com.tmsdemo.tradingmanagementsystem.controller;

/**
 * These class is used to taking the user and sending response
 * @author ShaikYaseen
 */
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tmsdemo.tradingmanagementsystem.dto.StockPurchaseRequest;
import com.tmsdemo.tradingmanagementsystem.exception.SharesNotAvailableException;
import com.tmsdemo.tradingmanagementsystem.exception.StockIdNotFoundException;
import com.tmsdemo.tradingmanagementsystem.exception.UserNotFoundException;
import com.tmsdemo.tradingmanagementsystem.response.UserResponse;
import com.tmsdemo.tradingmanagementsystem.response.UserSharesResponse;
import com.tmsdemo.tradingmanagementsystem.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	private static Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	/**
	 * Method is used to purchase the stock
	 * 
	 * @param stockId to fetch the stock
	 * @throws UserNotFoundException    when user doesn't exist
	 * @throws StockIdNotFoundException when user enters wrong stock id
	 */
	@PostMapping("/{userId}/shares")
	public ResponseEntity<UserResponse> purchaseStock(@PathVariable Long userId,
			@RequestBody StockPurchaseRequest purchaseRequest) {
		logger.info("enters into purchaseStock method to purchase Stock ");

		return new ResponseEntity<>(userService.purchaseStock(userId, purchaseRequest), HttpStatus.FOUND);
	}

	/**
	 * Method is used to fetch the share details
	 * 
	 * @param stockId to fetch the stock
	 * @throws SharesNotAvailableException when user enters wrong stok id
	 */
	@GetMapping("/{userId}/shares")
	public ResponseEntity<UserSharesResponse> getUserShares(@PathVariable("userId") Long userId) {
		logger.info("enters into getUserShares method to fetch Stock details");

		return new ResponseEntity<>(userService.getUserShares(userId), HttpStatus.FOUND);
	}
}
