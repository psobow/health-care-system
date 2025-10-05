package com.sobow.patientservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Patient {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    
    @NotNull
    private String name;
    
    @NotNull
    @Email
    @Column(unique = true)
    private String email;
    
    @NotNull
    private String address;
    
    @NotNull
    private LocalDate dateOfBirth;
    
    @NotNull
    private LocalDate registrationDate;
    
    @PrePersist
    public void prePersist() {
        registrationDate = LocalDate.now();
    }
}
