package com.example.PathoGoneVaccinationBookingSystem.Service;

import com.example.PathoGoneVaccinationBookingSystem.Dto.RequestDto.DoctorRequestDto;
import com.example.PathoGoneVaccinationBookingSystem.Dto.ResponseDto.CenterResponseDto;
import com.example.PathoGoneVaccinationBookingSystem.Dto.ResponseDto.DoctorResponseDto;
import com.example.PathoGoneVaccinationBookingSystem.Dto.ResponseDto.MaleDoctorAgeResponseDto;
import com.example.PathoGoneVaccinationBookingSystem.Exception.CenterNotFoundException;
import com.example.PathoGoneVaccinationBookingSystem.Model.Doctor;
import com.example.PathoGoneVaccinationBookingSystem.Model.VaccinationCenter;
import com.example.PathoGoneVaccinationBookingSystem.Repository.CenterRepository;
import com.example.PathoGoneVaccinationBookingSystem.Repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {
    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    CenterRepository centerRepository;

    public DoctorResponseDto addDoctor(DoctorRequestDto doctorRequestDto) {

        //check if center exist
        Optional<VaccinationCenter> optionalCenter = centerRepository.findById(doctorRequestDto.getCenterId());
        if(!optionalCenter.isPresent())
            throw new CenterNotFoundException("Invalid Center Id.");
        VaccinationCenter center = optionalCenter.get();

        //create doctor
        Doctor doctor = new Doctor();
        doctor.setName(doctorRequestDto.getName());
        doctor.setAge(doctorRequestDto.getAge());
        doctor.setEmailId(doctorRequestDto.getEmailId());
        doctor.setGender(doctorRequestDto.getGender());
        doctor.setCenter(center);

        //update center
        center.getDoctorList().add(doctor);
        VaccinationCenter savedCenter = centerRepository.save(center);

        //create resposeDto
        DoctorResponseDto doctorResponseDto = new DoctorResponseDto();
        doctorResponseDto.setName(doctor.getName());
        doctorResponseDto.setAge(doctor.getAge());
        doctorResponseDto.setEmailId(doctor.getEmailId());
        doctorResponseDto.setGender(doctor.getGender());

        CenterResponseDto centerResponseDto = new CenterResponseDto();
        centerResponseDto.setCenterName(savedCenter.getCenterName());
        centerResponseDto.setCenterType(savedCenter.getCenterType());
        centerResponseDto.setId(savedCenter.getId());
        centerResponseDto.setAddress(savedCenter.getAddress());

        doctorResponseDto.setCenter(centerResponseDto);

        return doctorResponseDto;
    }

    public List<MaleDoctorAgeResponseDto> getMaleDoctorsAboveAge(int age) {
        List<MaleDoctorAgeResponseDto> ansList = new ArrayList<>();
        List<Doctor> list = doctorRepository.findAll();
        for(Doctor d : list) {
            if(d.getGender().toString().equals("MALE") && d.getAge() > age) {
                MaleDoctorAgeResponseDto dto = new MaleDoctorAgeResponseDto();
                dto.setName(d.getName());
                dto.setAge(d.getAge());
                dto.setCenterAddress(d.getCenter().getAddress());

                ansList.add(dto);
            }
        }

        return ansList;
    }
}
