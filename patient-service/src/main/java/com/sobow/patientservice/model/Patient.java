package com.sobow.patientservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import java.time.LocalDate;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
public class Patient {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(unique = true, nullable = false)
    private String email;
    
    @Column(nullable = false)
    private String address;
    
    @Column(nullable = false)
    private LocalDate dateOfBirth;
    
    @Column(nullable = false, updatable = false)
    private LocalDate registrationDate;
    
    @PrePersist
    public void prePersist() {
        registrationDate = LocalDate.now();
    }
}
