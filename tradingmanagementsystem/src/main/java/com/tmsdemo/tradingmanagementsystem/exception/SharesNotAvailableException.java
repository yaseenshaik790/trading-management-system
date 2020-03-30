package com.tmsdemo.tradingmanagementsystem.exception;

/**
 * Exception to be throw when the stocks are not available with the stock name
 * 
 * @author ShaikYaseen
 *
 */
public class SharesNotAvailableException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private static final Integer ERROR_CODE = 659;

	public SharesNotAvailableException(Long userId) {
		super("Stocks are not available with user id : " + userId);
	}

	public Integer getErrorCode() {
		return ERROR_CODE;
	}

}
