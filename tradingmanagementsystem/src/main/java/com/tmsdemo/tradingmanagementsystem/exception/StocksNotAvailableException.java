package com.tmsdemo.tradingmanagementsystem.exception;

/**
 * Exception to be throw when the stocks are not available with the stock name
 * 
 * @author ShaikYaseen
 *
 */
public class StocksNotAvailableException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private static final Integer ERROR_CODE = 689;

	public StocksNotAvailableException(String stockName) {
		super("Stocks are not available with stock name : " + stockName);
	}

	public Integer getErrorCode() {
		return ERROR_CODE;
	}

}
