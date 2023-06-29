package com.example.eventapp.responseEntity;

import com.example.eventapp.entity.User;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserResponse {
    Long id;
    String username;

    public UserResponse(User user){
        this.id = user.getId();
        this.username= user.getUsername();
    }
}
