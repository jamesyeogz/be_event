package com.example.eventapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import com.example.eventapp.entity.Seating;
import com.example.eventapp.service.SeatingServiceImpl;


@RestController
@RequestMapping("seating")
public class SeatingContoller {

    @Autowired
    SeatingServiceImpl seatingServiceImpl;
    @GetMapping("/{id}")
    public ResponseEntity<Seating> getSeating(@PathVariable Long id){
        Seating seating = seatingServiceImpl.getSeatingByAttendanceId(id);
        return new ResponseEntity<>(seating, HttpStatus.OK);
    }   

    // @PostMapping
    // public ResponseEntity<HttpStatus> saveSeating(@RequestBody Seating seating){
    //     seatingServiceImpl.saveSeating(seating);
    //     return new ResponseEntity<>(HttpStatus.OK);
    // }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteSeating(@PathVariable Long id){
        seatingServiceImpl.deleteSeating(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/theatre/{id}")
    public ResponseEntity<List<Seating>> getSeatingsByTheatreId(@PathVariable Long id){
        List<Seating> seatings = seatingServiceImpl.getSeatingsByTheatreId(id);
        return new ResponseEntity<>(seatings, HttpStatus.OK);
    }
    
}
