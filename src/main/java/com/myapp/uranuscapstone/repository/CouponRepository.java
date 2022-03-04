package com.myapp.uranuscapstone.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myapp.uranuscapstone.model.Coupon;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Long> {

	
	
	

}
