package com.myapp.uranuscapstone.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myapp.uranuscapstone.model.Coupon;

import com.myapp.uranuscapstone.repository.CouponRepository;
@Service
public class CouponService {

	@Autowired
	CouponRepository couponRepository;
	
	public List<Coupon> getAllCoupon() {
		return couponRepository.findAll();
	}

	public Coupon saveCoupon(Coupon coupon) {
		return couponRepository.save(coupon);
	}

	public Optional<Coupon> getCouponById(long id) {
		return couponRepository.findById(id);
	}

	public Coupon updateCoupon(Coupon coupon) {
		return couponRepository.save(coupon);
	}

	
	public void deleteCouponById(Long id) {
		couponRepository.deleteById(id);	
	}
	
	
	}

