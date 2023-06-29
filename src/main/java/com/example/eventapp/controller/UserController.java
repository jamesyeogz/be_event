package com.example.eventapp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.eventapp.entity.User;
import com.example.eventapp.responseEntity.UserResponse;
import com.example.eventapp.service.UserServiceImpl;

import jakarta.validation.Valid;

@RestController
// @CrossOrigin("http://localhost:8080/")
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserServiceImpl userService;
    
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Long id){
        User user = userService.getUser(id);
        UserResponse response = new UserResponse(user);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> addUser(@Valid @RequestBody User user){
        User res = userService.saveUser(user);
        UserResponse response = new UserResponse(res);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable Long id){
        userService.deleteStudent(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserResponse>> getUsers() {
        List<UserResponse> res = new ArrayList<>();
        List<User> users = userService.getUsers();
        for(User user:users){
            UserResponse userResponse = new UserResponse(user);
            res.add(userResponse);
        }
        return new ResponseEntity<>(res,HttpStatus.OK);
    }
    
}
