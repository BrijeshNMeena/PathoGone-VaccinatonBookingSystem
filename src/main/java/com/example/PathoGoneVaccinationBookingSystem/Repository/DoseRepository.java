package com.example.PathoGoneVaccinationBookingSystem.Repository;

import com.example.PathoGoneVaccinationBookingSystem.Enum.DoseType;
import com.example.PathoGoneVaccinationBookingSystem.Model.Dose;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoseRepository extends JpaRepository<Dose, Integer> {

    List<Dose> findByDoseType(DoseType doseType);
}
