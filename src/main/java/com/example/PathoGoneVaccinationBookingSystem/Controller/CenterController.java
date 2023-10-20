package com.example.PathoGoneVaccinationBookingSystem.Controller;

import com.example.PathoGoneVaccinationBookingSystem.Dto.RequestDto.CenterRequestDto;
import com.example.PathoGoneVaccinationBookingSystem.Dto.ResponseDto.CenterResponseDto;
import com.example.PathoGoneVaccinationBookingSystem.Enum.CenterType;
import com.example.PathoGoneVaccinationBookingSystem.Service.CenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/center")
public class CenterController {
    @Autowired
    CenterService centerService;

    @PostMapping("/add_center")
    public ResponseEntity addCenter(@RequestBody CenterRequestDto centerRequestDto){
        CenterResponseDto centerResponseDto = centerService.addCenter(centerRequestDto);
        return new ResponseEntity<>(centerResponseDto, HttpStatus.CREATED);
    }

    @GetMapping("/center_of_a_type/{centerType}")
    public ResponseEntity centerOfType(@PathVariable CenterType centerType){
        List<CenterResponseDto> list = centerService.centerOfType(centerType);
        return new ResponseEntity<>(list, HttpStatus.ACCEPTED);
    }
}
