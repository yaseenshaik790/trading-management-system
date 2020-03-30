package com.tmsdemo.tradingmanagementsystem.response;

/**
 * UserResponse class is used to send the success message
 * @author ShaikYaseen
 */
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;

@Getter
public class UserResponse {

	@JsonProperty("message")
	private String message;
	@JsonProperty("statusCode")
	private Integer statusCode;

	public UserResponse(String message, Integer statusCode) {
		super();
		this.message = message;
		this.statusCode = statusCode;
	}

}
