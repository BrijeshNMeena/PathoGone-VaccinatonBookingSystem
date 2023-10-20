package com.example.PathoGoneVaccinationBookingSystem.Service;

import com.example.PathoGoneVaccinationBookingSystem.Controller.PersonController;
import com.example.PathoGoneVaccinationBookingSystem.Dto.RequestDto.AddPersonRequestDto;
import com.example.PathoGoneVaccinationBookingSystem.Dto.ResponseDto.AddPersonResponseDto;
import com.example.PathoGoneVaccinationBookingSystem.Exception.PersonNotFoundException;
import com.example.PathoGoneVaccinationBookingSystem.Model.Person;
import com.example.PathoGoneVaccinationBookingSystem.Repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    public AddPersonResponseDto addPerson(AddPersonRequestDto addPersonRequestDto) {

        Person person = new Person();
        person.setName(addPersonRequestDto.getName());
        person.setAge(addPersonRequestDto.getAge());
        person.setGender(addPersonRequestDto.getGender());
        person.setEmailId(addPersonRequestDto.getEmailId());

        Person savedPerson = personRepository.save(person);

        AddPersonResponseDto addPersonResponseDto = new AddPersonResponseDto();
        addPersonResponseDto.setName(savedPerson.getName());
        addPersonResponseDto.setMessage("Congrats, you have been registered.");

        return addPersonResponseDto;
    }

    public AddPersonResponseDto updateEmail(String oldEmail, String newEmail) {

        Person person = personRepository.findByEmailId(oldEmail);
        if(person == null) {
            throw new PersonNotFoundException("Email doesn't exist.");
        }

        person.setEmailId(newEmail);
        personRepository.save(person);
        AddPersonResponseDto responseDto = new AddPersonResponseDto();
        responseDto.setName(person.getName());
        responseDto.setMessage("Congratulation! Your email has been updated successfully");
        return responseDto;
    }

    public List<String> getAllVaccinatedPersons() {

        ArrayList<String> personList = new ArrayList<>();
        for(Person p : personRepository.findAll()) {
            if(p.isDoseTwoTaken())
                personList.add(p.getName());
        }

        return personList;
    }
}
