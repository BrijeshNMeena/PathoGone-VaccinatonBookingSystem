package com.example.PathoGoneVaccinationBookingSystem.Service;

import com.example.PathoGoneVaccinationBookingSystem.Dto.ResponseDto.CertificateResponseDto;
import com.example.PathoGoneVaccinationBookingSystem.Exception.PersonNotFoundException;
import com.example.PathoGoneVaccinationBookingSystem.Model.Certificate;
import com.example.PathoGoneVaccinationBookingSystem.Model.Person;
import com.example.PathoGoneVaccinationBookingSystem.Repository.CertificateRepository;
import com.example.PathoGoneVaccinationBookingSystem.Repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CertificateService {

    @Autowired
    CertificateRepository certificateRepository;

    @Autowired
    PersonRepository personRepository;

    public CertificateResponseDto addCertificate(int personId) {
        // check if person id present
        Optional<Person> optionalPerson = personRepository.findById(personId);
        if(optionalPerson.isEmpty())
            throw new PersonNotFoundException("Invalid person id");

        Person person = optionalPerson.get();

        // check if both doses are taken
        if(!person.isDoseTwoTaken())
            throw new RuntimeException("You need to take both the doses first in order to generate certificate");

        // create cetificate
        Certificate certificate = new Certificate();
        certificate.setCertificateNo(UUID.randomUUID().toString());
        certificate.setMessage("This is to certify that "+ person.getName()+ " has been fully vaccinated");
        certificate.setPerson(person);

        Certificate savedCerti = certificateRepository.save(certificate);

        //create certificate response dto
        CertificateResponseDto dto = new CertificateResponseDto();
        dto.setCertificateNo(savedCerti.getCertificateNo());
        dto.setDeclaration(savedCerti.getMessage());
        dto.setProductionDate(savedCerti.getProductionDate());
        dto.setMessage("Congrats! Your Certificate is generated.");
        dto.setPerson(person.getName());

        return dto;
    }

    public List<CertificateResponseDto> findByDate(Date date) {
        List<CertificateResponseDto> list = new ArrayList<>();
        List<Certificate> certificateList = certificateRepository.findAllByProductionDate(date);
        for(Certificate certificate : certificateList){
            CertificateResponseDto dto = new CertificateResponseDto();
            dto.setPerson(certificate.getPerson().getName());
            dto.setProductionDate(certificate.getProductionDate());
            dto.setCertificateNo(certificate.getCertificateNo());
            dto.setDeclaration(certificate.getMessage());
            dto.setMessage("Here are Your certification details");

            list.add(dto);
        }

        return list;
    }
}
