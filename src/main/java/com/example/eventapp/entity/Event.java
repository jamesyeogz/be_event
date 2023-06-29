package com.example.eventapp.entity;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Entity
@Table(name="event")
@Getter
@Setter
@RequiredArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @NonNull
    @Column(name="name")
    private String name;

    @NonNull
    @ManyToOne(optional = false)  
    @JoinColumn(name="user_id", referencedColumnName = "id")
    private User user;

    @Column(name="description",nullable = true)
    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private List<Attendance> attendances;
}
