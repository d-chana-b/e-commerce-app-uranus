package com.myapp.uranuscapstone.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.myapp.uranuscapstone.model.User;

@Repository
public interface UserRepository extends JpaRepository <User,Long> {
	/*
	 * @Query("SELECT u FROM User u WHERE u.email = ?1") public User
	 * findByEmail(String email);
	 */
	Optional<User> findUserByEmail(String email);
}
