package com.example.eventapp.service;

import com.example.eventapp.entity.Attendance;
import java.util.*;

public interface AttendanceService {
    Attendance getAttendanceByUserIdAndEventId(Long id, Long event_id);
    void deleteByUserIdAndEventId(Long user_id, Long event_id);
    void deleteByEventId(Long id);
    Attendance saveAttendance(Attendance attendance);
    List<Attendance> getAllAttendanceByUserId(Long id);
    List<Attendance> getAllAttendanceByEventId(Long id);
    void updateAttendancePresent(Long user_id, Long event_id);

}
