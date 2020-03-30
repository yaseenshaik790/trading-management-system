package com.tmsdemo.tradingmanagementsystem.repository;
/**
 * These class is used to perform database transactions
 * @author ShaikYaseen
 */
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tmsdemo.tradingmanagementsystem.entity.UserShares;

@Repository
public interface UserSharesRepository extends JpaRepository<UserShares, Long> {

	List<UserShares> findByUserId(Long userId);

}
