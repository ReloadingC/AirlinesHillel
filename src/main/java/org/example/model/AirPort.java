package org.example.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "airports")
public class AirPort {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

}
