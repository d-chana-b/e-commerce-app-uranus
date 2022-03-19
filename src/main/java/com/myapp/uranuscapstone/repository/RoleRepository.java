package com.myapp.uranuscapstone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myapp.uranuscapstone.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{

}
