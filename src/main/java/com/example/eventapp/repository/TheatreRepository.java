package com.example.eventapp.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.eventapp.entity.Theatre;

public interface TheatreRepository extends CrudRepository<Theatre,Long>{
    Optional<Theatre> findByName(String name);
}
