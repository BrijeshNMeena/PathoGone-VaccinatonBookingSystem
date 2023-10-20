package com.example.PathoGoneVaccinationBookingSystem.Repository;

import com.example.PathoGoneVaccinationBookingSystem.Model.VaccinationCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CenterRepository extends JpaRepository<VaccinationCenter, Integer> {
}
