package com.example.eventapp.service;

import java.util.List;

import com.example.eventapp.entity.User;

public interface UserService {
    User getUser(Long id);
    User saveUser(User user);
    void deleteStudent(Long id);
    List<User> getUsers();
}
