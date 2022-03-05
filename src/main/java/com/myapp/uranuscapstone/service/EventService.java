package com.myapp.uranuscapstone.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myapp.uranuscapstone.model.Event;
import com.myapp.uranuscapstone.repository.EventRepository;


@Service
public class EventService {

	@Autowired
	EventRepository eventRepository;
	
	public List<Event> getAllEvent() {
		return eventRepository.findAll();
	}

	public Event saveEvent(Event event) {
		return eventRepository.save(event);
	}

	public Optional<Event> getEventById(long id) {
		return eventRepository.findById(id);
	}

	public Event updateEvent(Event coupon) {
		return eventRepository.save(coupon);
	}

	
	public void deleteEventById(Long id) {
		eventRepository.deleteById(id);	
	}
	
	
	
	
	
	
}
