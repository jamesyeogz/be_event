package com.example.eventapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.eventapp.entity.Theatre;
import com.example.eventapp.service.TheatreServiceImpl;
import java.util.*;
@RestController
@RequestMapping("/theatre")
public class TheatreController {
    @Autowired
    TheatreServiceImpl theatreServiceImpl;

    @GetMapping("/{id}")
    public ResponseEntity<Theatre> getTheatre(@PathVariable Long id){
        Theatre theatre = theatreServiceImpl.getTheatre(id);
        return new ResponseEntity<>(theatre, HttpStatus.OK);
    }   

    @GetMapping
    public ResponseEntity<List<Theatre>> getTheatres(){
        return new ResponseEntity<>(theatreServiceImpl.getTheatres(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> saveTheatre(){
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteTheatre(){
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
