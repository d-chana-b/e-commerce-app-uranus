package com.myapp.uranuscapstone.repository;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myapp.uranuscapstone.model.Coupon;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Long> {

	public Coupon findByCouponName(Optional<String> couponName);

}
