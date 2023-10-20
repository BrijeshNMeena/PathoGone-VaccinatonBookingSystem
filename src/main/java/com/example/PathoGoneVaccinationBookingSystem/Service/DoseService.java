package com.example.PathoGoneVaccinationBookingSystem.Service;

import com.example.PathoGoneVaccinationBookingSystem.Dto.ResponseDto.BookDoseResponseDto;
import com.example.PathoGoneVaccinationBookingSystem.Enum.DoseType;
import com.example.PathoGoneVaccinationBookingSystem.Exception.DoseAlreadyTaken;
import com.example.PathoGoneVaccinationBookingSystem.Exception.PersonNotFoundException;
import com.example.PathoGoneVaccinationBookingSystem.Model.Dose;
import com.example.PathoGoneVaccinationBookingSystem.Model.Person;
import com.example.PathoGoneVaccinationBookingSystem.Repository.DoseRepository;
import com.example.PathoGoneVaccinationBookingSystem.Repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DoseService {

    @Autowired
    DoseRepository doseRepository;

    @Autowired
    PersonRepository personRepository;

    public BookDoseResponseDto getDose1(int personId, DoseType doseType) {
        Optional<Person> optionalPerson = personRepository.findById(personId);

        //check if person exists
        if(!optionalPerson.isPresent()) {
            throw new PersonNotFoundException("Invalid person id.");
        }

        Person person = optionalPerson.get();

        //check if dose one taken
        if(person.isDoseOneTaken()){
            throw new DoseAlreadyTaken("Dose One Already Administered.");
        }

        //create a dose
        Dose dose = new Dose();
        dose.setDoseId(String.valueOf(UUID.randomUUID()));
        dose.setDoseType(doseType);
        dose.setPerson(person);

        person.setDoseOneTaken(true);
        person.getDoseTaken().add(dose);
        personRepository.save(person);

        //return doseRepository.save(dose); //Saved through cascade by person

        //create doseResponse
        BookDoseResponseDto doseOne = new BookDoseResponseDto();
        doseOne.setMessage("Congrats! you have been given dose one");
        doseOne.setPersonName(person.getName());
        doseOne.setDoseType(doseType);

        return doseOne;
    }

    public BookDoseResponseDto getDose2(int personId, DoseType doseType) {
        Optional<Person> optionalPerson = personRepository.findById(personId);

        //check if person exists
        if(optionalPerson.isEmpty()) {
            throw new PersonNotFoundException("Invalid person id.");
        }

        Person person = optionalPerson.get();

        //check if dose one taken
        if(!person.isDoseOneTaken()){
            throw new DoseAlreadyTaken("First take Dose One.");
        }

        //check if dose two taken
        if(person.isDoseTwoTaken()){
            throw new DoseAlreadyTaken("Dose two has already given.");
        }

        //create a dose
        Dose dose = new Dose();
        dose.setDoseId(String.valueOf(UUID.randomUUID()));
        dose.setDoseType(doseType);
        dose.setPerson(person);

        person.setDoseTwoTaken(true);
        person.getDoseTaken().add(dose);
        personRepository.save(person);

        //return doseRepository.save(dose); //Saved through cascade by person

        //create doseResponse
        BookDoseResponseDto doseTwo = new BookDoseResponseDto();
        doseTwo.setMessage("Congrats! you have been fully vaccinated");
        doseTwo.setPersonName(person.getName());
        doseTwo.setDoseType(doseType);

        return doseTwo;
    }

    public long totalDosesGiven() {
        return doseRepository.count();
    }

    public long countDoseType(DoseType doseType) {
        List<Dose> list = doseRepository.findByDoseType(doseType);
        return list.size();
    }
}
