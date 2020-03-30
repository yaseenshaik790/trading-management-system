package com.tmsdemo.tradingmanagementsystem;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TradingManagementSystemApplicationTests {

	@Test
	void contextLoads() {
		assertEquals(5, Math.subtractExact(10, 5));
	}

}
