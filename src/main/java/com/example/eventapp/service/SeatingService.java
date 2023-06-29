package com.example.eventapp.service;
import java.util.*;
import com.example.eventapp.entity.Seating;

public interface SeatingService {
    List<Seating> getSeatingsByTheatreId(Long id);
    void saveSeating(Seating seating);
    void deleteSeating(Long id);
    Seating getSeatingByAttendanceId(Long id);
}
