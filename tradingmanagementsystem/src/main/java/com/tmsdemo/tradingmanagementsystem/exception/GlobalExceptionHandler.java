package com.tmsdemo.tradingmanagementsystem.exception;

/**
 * GlobalExceptionHandler class is used to handle all the exception classes
 * @author ShaikYaseen
 */
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(StocksNotAvailableException.class)
	public ResponseEntity<Object> stocksNotAvailableException(StocksNotAvailableException stocksNotAvailableException) {

		ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(stocksNotAvailableException.getMessage(),
				stocksNotAvailableException.getErrorCode());

		return new ResponseEntity<>(exceptionResponseDTO, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(StockIdNotFoundException.class)
	public ResponseEntity<Object> stockIdNotFoundException(StockIdNotFoundException stockIdNotFoundException) {

		ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(stockIdNotFoundException.getMessage(),
				stockIdNotFoundException.getErrorCode());

		return new ResponseEntity<>(exceptionResponseDTO, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(SharesNotAvailableException.class)
	public ResponseEntity<Object> sharesNotAvailableException(SharesNotAvailableException sharesNotAvailableException) {

		ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(sharesNotAvailableException.getMessage(),
				sharesNotAvailableException.getErrorCode());

		return new ResponseEntity<>(exceptionResponseDTO, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<Object> userNotFoundException(UserNotFoundException userNotFoundException) {

		ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(userNotFoundException.getMessage(),
				userNotFoundException.getErrorCode());

		return new ResponseEntity<>(exceptionResponseDTO, HttpStatus.NOT_FOUND);

	}

}
