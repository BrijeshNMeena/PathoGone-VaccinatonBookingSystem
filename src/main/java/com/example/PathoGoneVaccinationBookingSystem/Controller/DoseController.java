package com.example.PathoGoneVaccinationBookingSystem.Controller;

import com.example.PathoGoneVaccinationBookingSystem.Dto.ResponseDto.BookDoseResponseDto;
import com.example.PathoGoneVaccinationBookingSystem.Enum.DoseType;
import com.example.PathoGoneVaccinationBookingSystem.Service.DoseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dose")
public class DoseController {

    @Autowired
    DoseService doseService;

    @PostMapping("/get_dose_1")
    public ResponseEntity getDose1(@RequestParam("id") int personId, @RequestParam("dose_type") DoseType doseType) {
        try{
            BookDoseResponseDto doseOne = doseService.getDose1(personId, doseType);
            return new ResponseEntity<>(doseOne, HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/get_dose_2")
    public ResponseEntity getDose2(@RequestParam("id") int personId, @RequestParam("dose_type") DoseType doseType) {
        try{
            BookDoseResponseDto doseTwo = doseService.getDose2(personId, doseType);
            return new ResponseEntity<>(doseTwo, HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/total_dose")
    public ResponseEntity totalDosesGiven(){
        long count = doseService.totalDosesGiven();
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

    @GetMapping("/count_doseType/{doseType}")
    public ResponseEntity countDoseType(@PathVariable DoseType doseType) {
        long count = doseService.countDoseType(doseType);
        return new ResponseEntity<>(count, HttpStatus.OK);
    }
}
