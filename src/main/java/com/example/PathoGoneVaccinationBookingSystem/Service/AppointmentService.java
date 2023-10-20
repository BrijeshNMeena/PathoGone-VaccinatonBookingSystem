package com.example.PathoGoneVaccinationBookingSystem.Service;

import com.example.PathoGoneVaccinationBookingSystem.Dto.ResponseDto.AppointmentResponseDto;
import com.example.PathoGoneVaccinationBookingSystem.Exception.PersonNotFoundException;
import com.example.PathoGoneVaccinationBookingSystem.Model.Appointment;
import com.example.PathoGoneVaccinationBookingSystem.Model.Doctor;
import com.example.PathoGoneVaccinationBookingSystem.Model.Person;
import com.example.PathoGoneVaccinationBookingSystem.Repository.AppointmentRepository;
import com.example.PathoGoneVaccinationBookingSystem.Repository.DoctorRepository;
import com.example.PathoGoneVaccinationBookingSystem.Repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AppointmentService {

    @Autowired
    AppointmentRepository appointmentRepository;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    DoctorRepository doctorRepository;

    public AppointmentResponseDto bookAppointment(int personId, int doctorId) {
        //check if person exists
        Optional<Person> optionalPerson = personRepository.findById(personId);
        if(optionalPerson.isEmpty())
            throw new PersonNotFoundException("Invalid Person Id");

        //check if doc exists
        Optional<Doctor> optionalDoctor = doctorRepository.findById(doctorId);
        if(optionalDoctor.isEmpty())
            throw new RuntimeException("Invalid Doctor Id");

        Person person = optionalPerson.get();
        Doctor doctor = optionalDoctor.get();

        //Creating appointment object
        Appointment appointment = new Appointment();
        appointment.setAppointmentId(UUID.randomUUID().toString());
        appointment.setPerson(person);
        appointment.setDoctor(doctor);

        appointment = appointmentRepository.save(appointment);

        doctor.getAppointmentList().add(appointment);
        doctorRepository.save(doctor);

        person.getAppointmentList().add(appointment);
        Person savedPerson = personRepository.save(person);

        int size = savedPerson.getAppointmentList().size();
        Appointment savedAppointment = savedPerson.getAppointmentList().get(size-1);

        //creating appointment response dto
        AppointmentResponseDto dto = new AppointmentResponseDto();
        dto.setMessage("Congrats! your appointment has been booked successfully.");
        dto.setAppointmentId(savedAppointment.getAppointmentId());
        dto.setDate(savedAppointment.getDate());
        dto.setPerson(savedPerson.getName());
        dto.setAddress(doctor.getCenter().getAddress());
        dto.setDoctor(doctor.getName());

        return dto;
    }

    public List<AppointmentResponseDto> findAppointment(String appointmentId) {
        List<Appointment> appointmentList = appointmentRepository.findAllByAppointmentId(appointmentId);
        List<AppointmentResponseDto> ansList = new ArrayList<>();

//        if(appointment == null)
//            throw new RuntimeException("Invalid Appointment Id");

        for(Appointment appointment : appointmentList){
            //creating Appointment response dto
            AppointmentResponseDto dto = new AppointmentResponseDto();
            dto.setMessage("Here is Your Appointment details");
            dto.setDate(appointment.getDate());
            dto.setAppointmentId(appointmentId);
            dto.setPerson(appointment.getPerson().getName());
            dto.setDoctor(appointment.getDoctor().getName());
            dto.setAddress(appointment.getDoctor().getCenter().getAddress());

            ansList.add(dto);
        }


        return ansList;
    }
}
