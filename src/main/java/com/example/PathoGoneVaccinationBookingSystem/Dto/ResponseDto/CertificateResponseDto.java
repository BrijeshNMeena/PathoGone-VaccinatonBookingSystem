package com.example.PathoGoneVaccinationBookingSystem.Dto.ResponseDto;

import com.example.PathoGoneVaccinationBookingSystem.Model.Person;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CertificateResponseDto {

    String message;

    String certificateNo;

    String person;

    String declaration;

    Date productionDate;
}
