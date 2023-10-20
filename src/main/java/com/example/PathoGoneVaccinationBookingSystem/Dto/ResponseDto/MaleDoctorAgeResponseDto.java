package com.example.PathoGoneVaccinationBookingSystem.Dto.ResponseDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MaleDoctorAgeResponseDto {

    String name;

    int age;

    String centerAddress;
}
