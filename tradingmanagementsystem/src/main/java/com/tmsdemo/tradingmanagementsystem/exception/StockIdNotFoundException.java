package com.tmsdemo.tradingmanagementsystem.exception;

/**
 * Exception to be throw when the stocks are not available 
 * 
 * @author ShaikYaseen
 *
 */
public class StockIdNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private static final Integer ERROR_CODE = 679;

	public StockIdNotFoundException(Long stockId) {
		super("Stock not available with an stock id : " + stockId);
	}

	public Integer getErrorCode() {
		return ERROR_CODE;
	}

}
