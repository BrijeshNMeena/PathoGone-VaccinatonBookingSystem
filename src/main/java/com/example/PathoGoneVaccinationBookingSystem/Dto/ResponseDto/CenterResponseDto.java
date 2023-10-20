package com.example.PathoGoneVaccinationBookingSystem.Dto.ResponseDto;

import com.example.PathoGoneVaccinationBookingSystem.Enum.CenterType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CenterResponseDto {

    int id;

    String centerName;

    String address;

    CenterType centerType;
}
