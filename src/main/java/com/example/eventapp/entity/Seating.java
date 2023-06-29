package com.example.eventapp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;


@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="seating", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"theatre_id","seating"})
})
public class Seating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @OneToOne(optional = false)
    @JoinColumn(name="attendance_id",referencedColumnName = "id")
    private Attendance attendance;

    @ManyToOne(optional = false)
    @JoinColumn(name="theatre_id", referencedColumnName = "id")
    private Theatre theatre;

    @NotBlank(message = "A seat must be selected!")
    @NonNull
    @Column(name="seating")
    private String seating;

}
