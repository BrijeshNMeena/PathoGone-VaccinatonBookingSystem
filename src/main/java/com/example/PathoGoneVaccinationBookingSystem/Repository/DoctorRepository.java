package com.example.PathoGoneVaccinationBookingSystem.Repository;

import com.example.PathoGoneVaccinationBookingSystem.Model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
}
