package com.example.eventapp.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.eventapp.entity.Event;
import com.example.eventapp.repository.EventRepository;

@Service
public class EventServiceImpl implements EventService {
    @Autowired
    EventRepository eventRepository;

    @Override
    public Event getEvent(Long id){
        Optional<Event> event = eventRepository.findById(id);
        return unwrapEvent(event);
    }

    @Override
    public Event saveEvent(Event event){
        eventRepository.save(event);
        return event;
    }
    @Override
    public void deleteEvent(Long id){eventRepository.deleteById(id);}

    @Override
    public List<Event> getEventsByUserId(Long id){
        List<Event> events = eventRepository.findAllByUserId(id);
        return events;
    }

    static Event unwrapEvent(Optional<Event> entity) {
    if (entity.isPresent())
        return entity.get();
    else
        throw new RuntimeException("Event not found");
    }
}
