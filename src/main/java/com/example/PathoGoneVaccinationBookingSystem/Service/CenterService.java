package com.example.PathoGoneVaccinationBookingSystem.Service;

import com.example.PathoGoneVaccinationBookingSystem.Dto.RequestDto.CenterRequestDto;
import com.example.PathoGoneVaccinationBookingSystem.Dto.ResponseDto.CenterResponseDto;
import com.example.PathoGoneVaccinationBookingSystem.Enum.CenterType;
import com.example.PathoGoneVaccinationBookingSystem.Model.VaccinationCenter;
import com.example.PathoGoneVaccinationBookingSystem.Repository.CenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CenterService {
    @Autowired
    CenterRepository centerRepository;

    public CenterResponseDto addCenter(CenterRequestDto centerRequestDto) {

        //creating center
        VaccinationCenter center = new VaccinationCenter();
        center.setCenterName(centerRequestDto.getCenterName());
        center.setCenterType(centerRequestDto.getCenterType());
        center.setAddress(centerRequestDto.getAddress());

        VaccinationCenter savedCenter = centerRepository.save(center);

        //creating center ResposeDto
        CenterResponseDto centerResponseDto = new CenterResponseDto();
        centerResponseDto.setId(savedCenter.getId());
        centerResponseDto.setAddress(savedCenter.getAddress());
        centerResponseDto.setCenterType(savedCenter.getCenterType());
        centerResponseDto.setCenterName(savedCenter.getCenterName());

        return centerResponseDto;
    }

    public List<CenterResponseDto> centerOfType(CenterType centerType) {
        List<VaccinationCenter> centerList = centerRepository.findAll();
        List<CenterResponseDto> list = new ArrayList<>();

        for(VaccinationCenter vc : centerList){
            if(vc.getCenterType().equals(centerType)) {
                CenterResponseDto dto = new CenterResponseDto();
                dto.setId(vc.getId());
                dto.setCenterName(vc.getCenterName());
                dto.setAddress(vc.getAddress());
                dto.setCenterType(vc.getCenterType());

                list.add(dto);
            }
        }

        return list;
    }
}
