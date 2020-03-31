package com.tmsdemo.tradingmanagementsystem;

/**
 * TestData interface used for testing purpose
 * @author ShaikYaseen
 */
import java.time.LocalDate;

public interface TestData {

	public final static Long userId = 10l;
	public final static String userName = "Yaseen";
	public final static String password = "123";

	public final static Long stockId = 10l;
	public final static String stockName = "SBI";
	public final static Double stcokUnitPrice = 2000.00;
	public final static Integer stcokAvailable = 20;
	public final static LocalDate stcokDate = LocalDate.now();

	public final static Long stockId1 = 20l;
	public final static String stockName1 = "ICIC";
	public final static Double stcokUnitPrice1 = 4000.00;
	public final static Integer stcokAvailable1 = 40;
	public final static LocalDate stcokDate1 = LocalDate.now();

	public final static Long shareId = 10l;
	public final static LocalDate purchaseDate = LocalDate.now();
	public final static Double stcokPrice = 2000.00;
	public final static Integer stcokQuantity = 20;

	public final static Long shareId1 = 20l;
	public final static LocalDate purchaseDate1 = LocalDate.now();
	public final static Double stcokPrice1 = 4000.00;
	public final static Integer stcokQuantity1 = 40;

}
