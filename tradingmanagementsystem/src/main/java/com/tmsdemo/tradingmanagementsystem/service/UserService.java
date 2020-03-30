package com.tmsdemo.tradingmanagementsystem.service;
/**
 * interface for UserService
 * @author ShaikYaseen
 */
import com.tmsdemo.tradingmanagementsystem.dto.StockPurchaseRequest;
import com.tmsdemo.tradingmanagementsystem.response.UserResponse;
import com.tmsdemo.tradingmanagementsystem.response.UserSharesResponse;

public interface UserService {

	UserResponse purchaseStock(Long userId, StockPurchaseRequest purchaseRequest);

	UserSharesResponse getUserShares(Long userId);

}
