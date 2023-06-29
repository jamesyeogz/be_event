package com.example.eventapp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.eventapp.entity.Attendance;
import com.example.eventapp.entity.Event;
import com.example.eventapp.entity.Seating;
import com.example.eventapp.entity.Theatre;
import com.example.eventapp.entity.User;
import com.example.eventapp.responseEntity.TheatreSeatingRequest;
import com.example.eventapp.service.AttendanceServiceImpl;
import com.example.eventapp.service.EventServiceImpl;
import com.example.eventapp.service.SeatingServiceImpl;
import com.example.eventapp.service.TheatreServiceImpl;
import com.example.eventapp.service.UserServiceImpl;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {
    @Autowired
    AttendanceServiceImpl attendanceServiceImpl;

    @Autowired
    TheatreServiceImpl theatreServiceImpl;

    @Autowired
    UserServiceImpl userServiceImpl;

    @Autowired
    EventServiceImpl eventServiceImpl;

    @Autowired
    SeatingServiceImpl seatingServiceImpl;

    @GetMapping("/{event_id}/{user_id}")
    public ResponseEntity<Attendance> getAttendanceByUserIdAndEventId(@PathVariable Long user_id,
            @PathVariable Long event_id) {
        return new ResponseEntity<>(attendanceServiceImpl.getAttendanceByUserIdAndEventId(user_id, event_id),
                HttpStatus.OK);
    }

    @PostMapping("/create/{event_id}/{user_id}")
    public ResponseEntity<Attendance> addAttendance(@PathVariable Long event_id, @PathVariable Long user_id,
            @RequestBody Optional<TheatreSeatingRequest> theatreSeatingRequest) {
        Attendance attendance = new Attendance();
        Event event = eventServiceImpl.getEvent(event_id);
        User user = userServiceImpl.getUser(user_id);
        attendance.setUser(user);
        attendance.setEvent(event);
        Attendance saved_Attendance = attendanceServiceImpl.saveAttendance(attendance);
        if (theatreSeatingRequest.isPresent()) {
            TheatreSeatingRequest res = theatreSeatingRequest.get();
            Seating seating = new Seating();
            seating.setAttendance(saved_Attendance);
            Theatre theatre = theatreServiceImpl.getTheatre(res.getTheatre_id());
            seating.setTheatre(theatre);
            seating.setSeating(res.getSeat_number());
            seatingServiceImpl.saveSeating(seating);

        }

        return new ResponseEntity<>(saved_Attendance, HttpStatus.OK);
    }

    @GetMapping("/event/{id}")
    public ResponseEntity<List<Attendance>> getAllAttendanceByEventId(@PathVariable Long id) {
        List<Attendance> attendances = attendanceServiceImpl.getAllAttendanceByEventId(id);
        return new ResponseEntity<>(attendances, HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<Attendance>> getAllAttendanceByUserId(@PathVariable Long id) {
        return new ResponseEntity<List<Attendance>>(attendanceServiceImpl.getAllAttendanceByUserId(id), HttpStatus.OK);
    }

    @DeleteMapping("/{user_id}/{event_id}")
    public ResponseEntity<HttpStatus> deleteAttendance(@PathVariable Long user_id, @PathVariable Long event_id) {
        attendanceServiceImpl.deleteByUserIdAndEventId(user_id, event_id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/event/{id}")
    public ResponseEntity<HttpStatus> deleteAttendanceByEventId(@PathVariable Long id) {
        attendanceServiceImpl.deleteByEventId(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/user/{user_id}/event/{event_id}")

    public ResponseEntity<HttpStatus> updateAttendanceByUserIdAndEventId(@PathVariable Long user_id,
            @PathVariable Long event_id) {
        attendanceServiceImpl.updateAttendancePresent(user_id, event_id);
        return new ResponseEntity<HttpStatus>(HttpStatus.OK);
    }
}
