package com.tmsdemo.tradingmanagementsystem.entity;

/**
 * User class is used to store the user information 
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
@Table(name = "user")
@Getter
@Setter
@Data
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private Long userId;

	@Column(name = "user_name")
	private String userName;

	@Column(name = "user_email")
	private String email;

	@Column(name = "password")
	private String password;

	@Column(name = "date_of_birth")
	private LocalDate dateOfBirth;

}
