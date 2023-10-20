package com.example.PathoGoneVaccinationBookingSystem.Controller;

import com.example.PathoGoneVaccinationBookingSystem.Dto.RequestDto.DoctorRequestDto;
import com.example.PathoGoneVaccinationBookingSystem.Dto.ResponseDto.DoctorResponseDto;
import com.example.PathoGoneVaccinationBookingSystem.Dto.ResponseDto.MaleDoctorAgeResponseDto;
import com.example.PathoGoneVaccinationBookingSystem.Service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    DoctorService doctorService;

    @PostMapping("/add_doctor")
    public ResponseEntity addDoctor(@RequestBody DoctorRequestDto doctorRequestDto){
        try{
            DoctorResponseDto doctorResponseDto = doctorService.addDoctor(doctorRequestDto);
            return new ResponseEntity<>(doctorResponseDto, HttpStatus.CREATED);
        }
        catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/male_above_age")
    public ResponseEntity getMaleDoctorsAboveAge(@RequestParam("age") int age) {
        List<MaleDoctorAgeResponseDto> list = doctorService.getMaleDoctorsAboveAge(age);
        return new ResponseEntity<>(list, HttpStatus.ACCEPTED);
    }
}
