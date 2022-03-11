package com.myapp.uranuscapstone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myapp.uranuscapstone.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository <Category, Integer>{

}
