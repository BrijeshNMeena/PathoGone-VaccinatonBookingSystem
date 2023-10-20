package com.example.PathoGoneVaccinationBookingSystem.Repository;

import com.example.PathoGoneVaccinationBookingSystem.Model.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CertificateRepository extends JpaRepository<Certificate, Integer> {

    List<Certificate> findAllByProductionDate(Date date);

    Certificate findByCertificateNo(String certificateNo);
}
