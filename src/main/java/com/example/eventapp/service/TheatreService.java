package com.example.eventapp.service;

import com.example.eventapp.entity.Theatre;
import java.util.*;
public interface TheatreService {
    Theatre getTheatre(Long id);
    void deleteTheatre(Long id);
    Theatre saveTheatre(Theatre theatre);
    List<Theatre> getTheatres();
}
