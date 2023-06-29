package com.example.eventapp.repository;

import java.util.*;

import org.springframework.data.repository.CrudRepository;

import com.example.eventapp.entity.Attendance;

import jakarta.transaction.Transactional;

public interface AttendanceRepository extends CrudRepository<Attendance,Long>{
    Optional<Attendance> findByUserIdAndEventId(Long user_id, Long event_id);
    @Transactional
    void deleteByUserIdAndEventId(Long user_id, Long event_id);
    List<Attendance> findAllByUserId(Long id);
    List<Attendance> findAllByEventId(Long id);
    void deleteByEventId(Long id);
}
