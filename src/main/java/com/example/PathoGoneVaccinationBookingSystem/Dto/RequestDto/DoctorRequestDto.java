package com.example.PathoGoneVaccinationBookingSystem.Dto.RequestDto;

import com.example.PathoGoneVaccinationBookingSystem.Dto.ResponseDto.CenterResponseDto;
import com.example.PathoGoneVaccinationBookingSystem.Enum.Gender;
import com.example.PathoGoneVaccinationBookingSystem.Model.Appointment;
import com.example.PathoGoneVaccinationBookingSystem.Model.VaccinationCenter;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DoctorRequestDto {

    String name;

    int age;

    String emailId;

    Gender gender;

    int centerId;
}
