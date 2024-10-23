package com.pms.medication_service.model.prescription;

import com.pms.medication_service.model.MedicationStorage;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class RecommendMedication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer recommendMedicationId;
    private Double dosage;
    // Mapping to Prescription
    @ManyToOne
    @JoinColumn(name = "prescription_id")
    private Prescription prescription;

    // Mapping to MedicationStorage for checking available units
    @ManyToOne
    @JoinColumn(name = "medication_id")
    private MedicationStorage medicationStorage;
    //private Integer medicationId; // this is for checking the medication units are available or not
}
