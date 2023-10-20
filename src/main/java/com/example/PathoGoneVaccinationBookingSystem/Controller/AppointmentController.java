package com.example.PathoGoneVaccinationBookingSystem.Controller;

import com.example.PathoGoneVaccinationBookingSystem.Dto.ResponseDto.AppointmentResponseDto;
import com.example.PathoGoneVaccinationBookingSystem.Service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    @Autowired
    AppointmentService appointmentService;

    @PostMapping("/book_appointment")
    public ResponseEntity bookAppointment(@RequestParam("pId") int personId, @RequestParam("dId") int doctorId){
        try{
            AppointmentResponseDto appointmentResponseDto = appointmentService.bookAppointment(personId, doctorId);
            return new ResponseEntity<>(appointmentResponseDto, HttpStatus.CREATED);
        }
        catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get_appointment/{appointmentId}")
    public ResponseEntity findAppointment(@PathVariable String appointmentId){
        try{
            List<AppointmentResponseDto> dto = appointmentService.findAppointment(appointmentId);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        }
        catch (RuntimeException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
