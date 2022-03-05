package com.myapp.uranuscapstone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myapp.uranuscapstone.model.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

}
