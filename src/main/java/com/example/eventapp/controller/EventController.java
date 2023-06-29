package com.example.eventapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.eventapp.entity.Event;
import com.example.eventapp.entity.User;
import com.example.eventapp.service.EventServiceImpl;
import com.example.eventapp.service.UserServiceImpl;

@RestController
@RequestMapping("/event")
public class EventController {
    @Autowired
    EventServiceImpl eventServiceImpl;
    @Autowired
    UserServiceImpl userServiceImpl;
    @GetMapping("/{id}")
    public ResponseEntity<Event> getEvent(@PathVariable Long id){
        return new ResponseEntity<>(eventServiceImpl.getEvent(id), HttpStatus.OK);
    }

    @PostMapping("/user/{id}")
    public ResponseEntity<Event> addEvent(@RequestBody Event event,@PathVariable Long id){
        User user = userServiceImpl.getUser(id);
        event.setUser(user);
        Event result = eventServiceImpl.saveEvent(event);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteEvent(@PathVariable Long id){
        eventServiceImpl.deleteEvent(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<Event>> getEventsByUserId(@PathVariable Long id){
        return new ResponseEntity<>(eventServiceImpl.getEventsByUserId(id),HttpStatus.OK);
    }

}
