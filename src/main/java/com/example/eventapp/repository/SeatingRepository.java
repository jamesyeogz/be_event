package com.example.eventapp.repository;

import org.springframework.data.repository.CrudRepository;
import java.util.*;
import com.example.eventapp.entity.Seating;

public interface SeatingRepository extends CrudRepository<Seating,Long>{
    Optional<Seating> findByAttendanceId(Long id);
    List<Seating> findAllByTheatreId(Long id);
    Optional<Seating> findByTheatreIdAndSeating(Long id, String seating);
}
