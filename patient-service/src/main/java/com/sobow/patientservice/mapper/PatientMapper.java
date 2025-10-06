package com.sobow.patientservice.mapper;

import com.sobow.patientservice.dto.PatientResponseDTO;
import com.sobow.patientservice.model.Patient;

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
}
