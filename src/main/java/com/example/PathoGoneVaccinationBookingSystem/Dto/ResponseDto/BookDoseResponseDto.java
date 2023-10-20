package com.example.PathoGoneVaccinationBookingSystem.Dto.ResponseDto;

import com.example.PathoGoneVaccinationBookingSystem.Enum.DoseType;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.FieldDefaults;


@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BookDoseResponseDto {

    String message;

    String personName;

    DoseType doseType;
}
