package com.pms.medication_service.services;

import com.pms.medication_service.dto.PrescriptionDTO;
import com.pms.medication_service.dto.RecommendMedicationDTO;
import com.pms.medication_service.model.MedicationBlock;
import com.pms.medication_service.model.MedicationStorage;
import com.pms.medication_service.model.prescription.Prescription;
import com.pms.medication_service.model.prescription.RecommendMedication;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PrescriptionServiceMapper {
    public Prescription toPrescription(PrescriptionDTO prescriptionDTO) {
        LocalDate date = LocalDate.now();
        return Prescription.builder()
                .placementId(prescriptionDTO.placementId())
                .date(date)
                .build();
    }

    public MedicationBlock toMedicationBlock(MedicationStorage medicationStorage, PrescriptionDTO prescriptionDTO, RecommendMedicationDTO recommendMedicationDTO) {
        return MedicationBlock.builder()
                .block_quantity(recommendMedicationDTO.dosage())
                .medication_storage(medicationStorage)
                .placement_id(prescriptionDTO.placementId())
                .build();
    }

    public RecommendMedication toRecommendMedication(Prescription savedPrescription, MedicationStorage medicationStorage, RecommendMedicationDTO recommendMedicationDTO) {
        return RecommendMedication.builder()
                .dosage(recommendMedicationDTO.dosage())
                .medicationStorage(medicationStorage)
                .prescription(savedPrescription)
                .build();
    }
}
