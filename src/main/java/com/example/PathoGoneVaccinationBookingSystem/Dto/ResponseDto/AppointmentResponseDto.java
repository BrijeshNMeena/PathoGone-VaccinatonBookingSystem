package com.example.PathoGoneVaccinationBookingSystem.Dto.ResponseDto;

import com.example.PathoGoneVaccinationBookingSystem.Model.Doctor;
import com.example.PathoGoneVaccinationBookingSystem.Model.Person;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AppointmentResponseDto {

    String message;

    String appointmentId;

    Date date;

    String person;

    String doctor;

    String address;
}
