package com.example.PathoGoneVaccinationBookingSystem.Dto.RequestDto;

import com.example.PathoGoneVaccinationBookingSystem.Enum.CenterType;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CenterRequestDto {

    String centerName;

    String address;

    CenterType centerType;
}
