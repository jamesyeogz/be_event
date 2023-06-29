package com.example.eventapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import com.example.eventapp.entity.Attendance;
import com.example.eventapp.repository.AttendanceRepository;

@Service
public class AttendanceServiceImpl implements AttendanceService {
    @Autowired
    AttendanceRepository attendanceRepository;

    @Override
    public Attendance getAttendanceByUserIdAndEventId(Long user_id,Long event_id) {
        Optional<Attendance> attendance = attendanceRepository.findByUserIdAndEventId(user_id, event_id);
        return unwrapAttendance(attendance);
    }

    @Override
    public void deleteByUserIdAndEventId(Long user_id, Long event_id) {
        attendanceRepository.deleteByUserIdAndEventId(user_id, event_id);
    }

    @Override
    public void deleteByEventId(Long id) {
        attendanceRepository.deleteByEventId(id);
    }

    @Override
    public Attendance saveAttendance(Attendance attendance){
        attendanceRepository.save(attendance);
        return attendance;
    }

    @Override
    public List<Attendance> getAllAttendanceByUserId(Long id){
        List<Attendance> attendances = attendanceRepository.findAllByUserId(id);
        return attendances;
    }

    @Override
    public List<Attendance> getAllAttendanceByEventId(Long id){
        List<Attendance> attendances = attendanceRepository.findAllByEventId(id);
        return attendances;
    }

    @Override
    public void updateAttendancePresent(Long user_id, Long event_id){
        Optional<Attendance> res = attendanceRepository.findByUserIdAndEventId(user_id, event_id);
        Attendance attendance = unwrapAttendance(res);
        attendance.setAttended(true);
        attendanceRepository.save(attendance);
    }


    public static Attendance unwrapAttendance(Optional<Attendance> entity) {
        if (entity.isPresent()) {
            return entity.get();
        } else {
            throw new RuntimeException("Attendance not found!");
        }
    }

}
