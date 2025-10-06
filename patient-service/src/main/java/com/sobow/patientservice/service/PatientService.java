package com.sobow.patientservice.service;

import com.sobow.patientservice.dto.PatientResponseDTO;
import com.sobow.patientservice.mapper.PatientMapper;
import com.sobow.patientservice.model.Patient;
import com.sobow.patientservice.repository.PatientRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PatientService {
    private final PatientRepository patientRepository;
    
    public List<PatientResponseDTO> findAll() {
        List<Patient> patients = patientRepository.findAll();
        return patients.stream()
                       .map(PatientMapper::toDTO)
                       .toList();
    }
    
    
}
