package com.example.PathoGoneVaccinationBookingSystem.Model;

import com.example.PathoGoneVaccinationBookingSystem.Enum.CenterType;
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
public class VaccinationCenter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String centerName;

    String address;

    @Enumerated(value = EnumType.STRING)
    CenterType centerType;

    @OneToMany(mappedBy = "center", cascade = CascadeType.ALL)
    List<Doctor> doctorList = new ArrayList<>();

}
