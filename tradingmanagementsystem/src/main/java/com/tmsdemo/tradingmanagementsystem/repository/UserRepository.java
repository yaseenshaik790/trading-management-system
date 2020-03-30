package com.tmsdemo.tradingmanagementsystem.repository;

/**
 * These class is used to perform database transactions
 * @author ShaikYaseen
 */
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tmsdemo.tradingmanagementsystem.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
