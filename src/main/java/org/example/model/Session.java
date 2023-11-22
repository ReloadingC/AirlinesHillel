package org.example.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "sessions")
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private LocalDateTime startData;

    @OneToOne
    @JoinColumn(name = "airport_id")
    private AirPort airPort;

    @ManyToOne
    @JoinColumn(name = "flight_id")
    private Flight flight;


}
