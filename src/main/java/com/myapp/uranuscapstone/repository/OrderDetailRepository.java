package com.myapp.uranuscapstone.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myapp.uranuscapstone.model.OrderDetail;
import com.myapp.uranuscapstone.model.User;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail,Long> {
	public List<OrderDetail> findByUser(User user);
}
