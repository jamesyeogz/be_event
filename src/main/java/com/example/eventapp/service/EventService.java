package com.example.eventapp.service;

import com.example.eventapp.entity.Event;
import java.util.*;
public interface EventService {
    Event getEvent(Long id);
    void deleteEvent(Long id);
    Event saveEvent(Event event);
    List<Event> getEventsByUserId(Long id);
}
