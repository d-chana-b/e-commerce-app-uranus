package com.myapp.uranuscapstone.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.myapp.uranuscapstone.model.Users;

public interface UserRepository extends JpaRepository <Users,Long> {

}
