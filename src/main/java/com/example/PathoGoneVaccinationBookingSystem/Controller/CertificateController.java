package com.example.PathoGoneVaccinationBookingSystem.Controller;

import com.example.PathoGoneVaccinationBookingSystem.Dto.ResponseDto.CertificateResponseDto;
import com.example.PathoGoneVaccinationBookingSystem.Service.CertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/certificate")
public class CertificateController {

    @Autowired
    CertificateService certificateService;

    @PostMapping("/generate_certificate")
    public ResponseEntity addCertificate(@RequestParam("pId") int personId) {
        try{
            CertificateResponseDto dto = certificateService.addCertificate(personId);
            return new ResponseEntity<>(dto, HttpStatus.CREATED);
        }
        catch (RuntimeException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/find_using_date")
    public ResponseEntity findByDate(@RequestParam("date") Date date){
        List<CertificateResponseDto> list = certificateService.findByDate(date);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
