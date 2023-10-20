package com.example.PathoGoneVaccinationBookingSystem.Model;

import com.example.PathoGoneVaccinationBookingSystem.Enum.Gender;
import jakarta.annotation.Generated;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String name;

    int age;

    @Column(unique = true)
    String emailId;

    @Enumerated(EnumType.STRING)
    Gender gender;

    boolean doseOneTaken;

    boolean doseTwoTaken;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    List<Dose> doseTaken = new ArrayList<>();

    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL)
    Certificate certificate;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    List<Appointment> appointmentList = new ArrayList<>();
}
