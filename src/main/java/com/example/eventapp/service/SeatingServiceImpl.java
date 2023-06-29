package com.example.eventapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import com.example.eventapp.entity.Seating;
import com.example.eventapp.exception.EntityNotFoundException;
import com.example.eventapp.repository.SeatingRepository;

@Service
public class SeatingServiceImpl implements SeatingService{
    @Autowired
    SeatingRepository seatingRepository;

    @Override
    public Seating getSeatingByAttendanceId(Long id){
        Optional<Seating> seat = seatingRepository.findByAttendanceId(id);
        return unwrapSeating(seat, id);
    }
    @Override
    public void saveSeating(Seating seating){
        
        return;
    }
    @Override
    public void deleteSeating(Long id){
        return;
    }

    @Override
    public List<Seating> getSeatingsByTheatreId(Long id){
        List<Seating> seats = seatingRepository.findAllByTheatreId(id);
        return seats;
    }

    static Seating unwrapSeating(Optional<Seating> entity, Long id){
        if(entity.isPresent()){
            return entity.get();
        }else{
            throw new EntityNotFoundException(id, Seating.class);
        }
    }
}
