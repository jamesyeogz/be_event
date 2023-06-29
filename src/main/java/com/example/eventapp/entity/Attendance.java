package com.example.eventapp.entity;

import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "attendance", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"user_id","event_id"})
})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // What if we do the normal composite primary key instead of this method?
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "event_id", referencedColumnName = "id")
    private Event event;

    // @Id
    // @ManyToOne
    // @JoinColumn(name = "attendeeId", referencedColumnName = "id")
    // private User user;

    // @Id
    // @ManyToOne
    // @JoinColumn(name = "eventId", referencedColumnName = "id")
    // private Event event;

    @Column(name = "attendance_date")
    private LocalDateTime date = LocalDateTime.now();

    @Column(name = "attended")
    private boolean attended = false;

    @OneToOne(mappedBy = "attendance", cascade = CascadeType.ALL)
    @JoinTable(
        name = "seating_plan",
        joinColumns = @JoinColumn(name = "attendance_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "seating_id", referencedColumnName = "id")
    )
    private Seating seating;

}
