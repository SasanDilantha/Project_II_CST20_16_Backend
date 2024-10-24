package com.pms.medication_service.model.prescription;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class Prescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer prescriptionId;
    private Integer placementId;
    private LocalDate date;
    // Mapping to multiple RecommendMedication objects in a single prescription
    @OneToMany(mappedBy = "prescription", cascade = CascadeType.ALL)
    private List<RecommendMedication> recommendMedications;
}
