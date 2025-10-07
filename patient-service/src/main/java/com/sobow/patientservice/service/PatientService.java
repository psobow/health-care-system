package com.sobow.patientservice.service;

import com.sobow.patientservice.dto.PatientRequestDTO;
import com.sobow.patientservice.dto.PatientResponseDTO;
import com.sobow.patientservice.exception.EmailAlreadyExistsException;
import com.sobow.patientservice.exception.PatientNotFoundException;
import com.sobow.patientservice.mapper.PatientMapper;
import com.sobow.patientservice.model.Patient;
import com.sobow.patientservice.repository.PatientRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    
    public PatientResponseDTO create(PatientRequestDTO dto) {
        if (patientRepository.existsByEmail(dto.email())) {
            throw new EmailAlreadyExistsException("A patient with this email already exists: " + dto.email());
        }
        
        Patient patient = patientRepository.save(PatientMapper.toEntity(dto));
        return PatientMapper.toDTO(patient);
    }
    
    @Transactional
    public PatientResponseDTO update(UUID uuid, PatientRequestDTO patch) {
        Patient patient = patientRepository.findById(uuid).orElseThrow(() -> new PatientNotFoundException(
            "Patient not found with ID: " + uuid));
        
        if (patientRepository.existsByEmailAndIdNot(patch.email(), uuid)) {
            throw new EmailAlreadyExistsException("A patient with this email already exists: " + patch.email());
        }
        
        patient.setName(patch.name());
        patient.setAddress(patch.address());
        patient.setEmail(patch.email());
        patient.setDateOfBirth(LocalDate.parse(patch.dateOfBirth()));
        
        Patient updatedPatient = patientRepository.save(patient);
        
        return PatientMapper.toDTO(updatedPatient);
    }
}
