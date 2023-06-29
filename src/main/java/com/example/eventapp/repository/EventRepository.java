package com.example.eventapp.repository;

import org.springframework.data.repository.CrudRepository;
import java.util.*;
import com.example.eventapp.entity.Event;

public interface EventRepository extends CrudRepository<Event, Long>{
    List<Event> findAllByUserId(Long id);
}
