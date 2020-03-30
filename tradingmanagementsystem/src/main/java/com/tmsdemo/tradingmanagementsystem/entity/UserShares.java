package com.tmsdemo.tradingmanagementsystem.entity;

/**
 * UserShares class is used to store the user shares information 
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
@Table(name = "user_shares")
@Getter
@Setter
@Data
public class UserShares implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@Column(name = "stock_id")
	private Long stockId;

	@Column(name = "stock_price")
	private Double stockPrice;

	@Column(name = "share_quantity")
	private Integer stockQuantity;

	@Column(name = "purchase_date")
	private LocalDate purchaseDate;
	
	@Column(name = "user_id")
	private Long userId;

}
