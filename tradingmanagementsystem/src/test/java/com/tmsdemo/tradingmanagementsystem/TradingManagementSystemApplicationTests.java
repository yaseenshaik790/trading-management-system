package com.tmsdemo.tradingmanagementsystem;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * TradingManagementSystemApplicationTests class is used to run test classes
 * 
 * @author ShaikYaseen
 *
 */
@SpringBootTest
class TradingManagementSystemApplicationTests {

	@Test
	void contextLoads() {
		assertEquals(5, Math.subtractExact(10, 5));
	}

}
