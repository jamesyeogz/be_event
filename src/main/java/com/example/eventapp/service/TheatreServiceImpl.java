package com.example.eventapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.eventapp.entity.Theatre;
import com.example.eventapp.exception.EntityNotFoundException;
import com.example.eventapp.repository.TheatreRepository;
import java.util.*;
@Service
public class TheatreServiceImpl implements TheatreService{
    @Autowired
    TheatreRepository theatreRepository;

    @Override
    public Theatre getTheatre(Long id){
        Optional<Theatre> theatre = theatreRepository.findById(id);
        return unwrapTheatre(theatre, id);
    }

    @Override 
    public void deleteTheatre(Long id){
        theatreRepository.deleteById(id);
        return;
    }
    @Override
    public Theatre saveTheatre(Theatre theatre){
        Theatre res = theatreRepository.save(theatre);
        return res;
    }

    @Override
    public List<Theatre> getTheatres(){
        List<Theatre> theatres = (List<Theatre>) theatreRepository.findAll();
        return theatres;
    }



        static Theatre unwrapTheatre(Optional<Theatre> entity, Long id){
        if(entity.isPresent()){
            return entity.get();
        }else{
            throw new EntityNotFoundException(id, Theatre.class);
        }
    }
    

}
