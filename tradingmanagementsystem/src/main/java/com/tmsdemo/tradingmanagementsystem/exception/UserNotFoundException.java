package com.tmsdemo.tradingmanagementsystem.exception;

/**
 * Exception to be throw when the stocks are not available with the stock name
 * 
 * @author ShaikYaseen
 *
 */
public class UserNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private static final Integer ERROR_CODE = 669;

	public UserNotFoundException(Long userId) {
		super("User not found  with an user id: " + userId);
	}

	public Integer getErrorCode() {
		return ERROR_CODE;
	}

}
