package com.myapp.uranuscapstone.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myapp.uranuscapstone.model.User;


public interface UserRepository extends JpaRepository <User,Long> {

}
