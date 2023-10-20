package com.example.PathoGoneVaccinationBookingSystem.Dto.ResponseDto;

import com.example.PathoGoneVaccinationBookingSystem.Enum.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DoctorResponseDto {

    String name;

    int age;

    String emailId;

    Gender gender;

    CenterResponseDto center;
}
