package com.example.PathoGoneVaccinationBookingSystem.Controller;

import com.example.PathoGoneVaccinationBookingSystem.Dto.RequestDto.AddPersonRequestDto;
import com.example.PathoGoneVaccinationBookingSystem.Dto.ResponseDto.AddPersonResponseDto;
import com.example.PathoGoneVaccinationBookingSystem.Model.Person;
import com.example.PathoGoneVaccinationBookingSystem.Service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    PersonService personService;

    @PostMapping("/add-person")
    public ResponseEntity addPerson(@RequestBody AddPersonRequestDto addPersonRequestDto) {
        try{
            AddPersonResponseDto responsePerson = personService.addPerson(addPersonRequestDto);
            return new ResponseEntity(responsePerson, HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity("Email already exits.", HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping("/update_email")
    public ResponseEntity updateEmail(@RequestParam("oldEmail") String oldEmail, @RequestParam("newEmail") String newEmail) {
        try{
            AddPersonResponseDto responseDto = personService.updateEmail(oldEmail, newEmail);
            return new ResponseEntity<>(responseDto, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get_vaccinated_people")
    public ResponseEntity getAllVaccinatedPersons() {
        List<String> personList = personService.getAllVaccinatedPersons();
        return new ResponseEntity<>(personList, HttpStatus.OK);
    }
}
