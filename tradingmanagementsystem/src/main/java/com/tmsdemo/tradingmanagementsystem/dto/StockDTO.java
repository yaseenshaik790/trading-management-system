package com.tmsdemo.tradingmanagementsystem.dto;

/**
 * StockDTO class is used to transfer the stock details to the client
 * @author ShaikYaseen
 */
import java.time.LocalDate;

import lombok.Setter;
import lombok.Data;
import lombok.Getter;

@Getter
@Setter
@Data
public class StockDTO {

	private Long stockId;

	private String stockName;

	private Double stockUnitPrice;

	private Integer stockAvailable;

	private LocalDate stockDate;
}
