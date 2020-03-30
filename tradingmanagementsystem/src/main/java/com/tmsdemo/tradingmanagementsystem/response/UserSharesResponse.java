package com.tmsdemo.tradingmanagementsystem.response;

/**
 * UserShareResponse class is used to send the history of user shares
 * @author ShaikYaseen
 */
import java.util.List;

import com.tmsdemo.tradingmanagementsystem.entity.UserShares;

import lombok.Getter;

@Getter

public class UserSharesResponse {
	private List<UserShares> shares;
	private Integer sharesListSize;

	public UserSharesResponse(List<UserShares> shares, Integer sharesListSize) {
		super();
		this.shares = shares;
		this.sharesListSize = sharesListSize;
	}

}
