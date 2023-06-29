package com.example.eventapp.responseEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class TheatreSeatingRequest {
    private Long theatre_id;
    private String seat_number;
}
