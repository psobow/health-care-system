package com.sobow.patientservice.controller;

import com.sobow.patientservice.dto.PatientRequestDTO;
import com.sobow.patientservice.dto.PatientResponseDTO;
import com.sobow.patientservice.service.PatientService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientController {
    
    private final PatientService patientService;
    
    @GetMapping
    public ResponseEntity<List<PatientResponseDTO>> getAllPatients() {
        List<PatientResponseDTO> patients = patientService.findAll();
        return ResponseEntity.ok(patients);
    }
    
    @PostMapping
    public ResponseEntity<PatientResponseDTO> createPatient(
        @RequestBody @Valid PatientRequestDTO dto
    ) {
        PatientResponseDTO response = patientService.create(dto);
        return ResponseEntity.ok(response);
    }
}
