package com.sobow.patientservice.mapper;

import com.sobow.patientservice.dto.PatientRequestDTO;
import com.sobow.patientservice.dto.PatientResponseDTO;
import com.sobow.patientservice.model.Patient;
import java.time.LocalDate;

public class PatientMapper {
    public static PatientResponseDTO toDTO(Patient p) {
        return PatientResponseDTO.builder()
            .id(p.getId().toString())
            .name(p.getName())
            .email(p.getEmail())
            .address(p.getAddress())
            .dateOfBirth(p.getDateOfBirth().toString())
            .build();
    }
    
    public static Patient toEntity(PatientRequestDTO dto) {
        return Patient.builder()
            .name(dto.name())
            .email(dto.email())
            .address(dto.address())
            .dateOfBirth(LocalDate.parse(dto.dateOfBirth()))
            .build();
    }
}
