package com.tmsdemo.tradingmanagementsystem.entity;

/**
 * Stock class is used to store the stock information 
 * @author ShaikYaseen
 */
import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "stock")
@Getter
@Setter
@Data
public class Stock implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "stock_id")
	private Long stockId;

	@Column(name = "stock_name")
	private String stockName;

	@Column(name = "stock_unit_price")
	private Double stockUnitPrice;

	@Column(name = "stock_available")
	private Integer stockAvailable;

	@Column(name = "stock_date")
	private LocalDate stockDate;
	

}
