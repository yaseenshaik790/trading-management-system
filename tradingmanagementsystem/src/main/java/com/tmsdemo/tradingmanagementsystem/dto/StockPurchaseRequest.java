package com.tmsdemo.tradingmanagementsystem.dto;

/**
 * StockPurchaseRequest class is used to send the stock purchase request
 * @author ShaikYaseen
 */
import lombok.Setter;

import lombok.Getter;

@Getter
@Setter
public class StockPurchaseRequest {

	private Long stockId;

	private Integer quantity;
}
