package com.pms.medication_service.services;

import com.pms.medication_service.dto.PrescriptionDTO;
import com.pms.medication_service.dto.RecommendMedicationDTO;
import com.pms.medication_service.model.MedicationBlock;
import com.pms.medication_service.model.MedicationInventory;
import com.pms.medication_service.model.MedicationStorage;
import com.pms.medication_service.model.prescription.Prescription;
import com.pms.medication_service.model.prescription.RecommendMedication;
import com.pms.medication_service.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PrescriptionService {
    private final PrescriptionRepository prescriptionRepository;
    private final RecommendMedicationRepository recommendMedicationRepository;
    private final MedicationBlockRepository medicationBlockRepository;
    private final MedicationInventoryRepository medicationInventoryRepository;

    private final PrescriptionServiceMapper prescriptionServiceMapper;

    // Method to add a new prescription using placementId to get medicationId
    public String addPrescription(PrescriptionDTO prescriptionDTO) {

        // Loop through each recommended medication to check availability
        for (RecommendMedicationDTO recommendMedicationDTO : prescriptionDTO.recommendMedications()) {

            // Fetch MedicationBlock using placementId
            Optional<MedicationBlock> medicationBlockOptional =
                    medicationBlockRepository.findLatestByPlacementId(prescriptionDTO.placementId());

            // Check if the MedicationBlock exists
            if (medicationBlockOptional.isEmpty()) {
                return sendAlert("Placement with ID " + prescriptionDTO.placementId() + " not found.");
            }

            MedicationBlock medicationBlock = medicationBlockOptional.get();
            MedicationStorage medicationStorage = medicationBlock.getMedication_storage();  // Get MedicationStorage

            // Now, we can get the medicationId from MedicationStorage
            Integer medicationId = medicationStorage.getMedication_id();
            // Get the available Dose of the medication
            MedicationInventory medInventory = medicationStorage.getMedicationInventory();
            double currentAvailableQuantity = medInventory.getAvailable_quantity();

            // Check if the required quantity is available
            if (currentAvailableQuantity < recommendMedicationDTO.dosage()) {
                return sendAlert("Insufficient medication units for Medication ID " + medicationId +
                        ". Available: " + currentAvailableQuantity + ", Required: " + recommendMedicationDTO.dosage());
            }
        }

        // If all medications are available, proceed to create Prescription entity and save it
        var savedPrescription = prescriptionRepository.save(prescriptionServiceMapper.toPrescription(prescriptionDTO));

        // Reduce the available quantity in MedicationStorage for each medication
        for (RecommendMedicationDTO recommendMedicationDTO : prescriptionDTO.recommendMedications()) {
            Optional<MedicationBlock> medicationBlockOptional =
                    medicationBlockRepository.findLatestByPlacementId(prescriptionDTO.placementId());

            if (medicationBlockOptional.isPresent()) {
                MedicationStorage medicationStorage = medicationBlockOptional.get().getMedication_storage();
                MedicationInventory medInventory = medicationStorage.getMedicationInventory();
                medInventory.setAvailable_quantity(
                        medInventory.getAvailable_quantity() - recommendMedicationDTO.dosage()
                );
                // update the MedicationInventory with the new available quantity
                medicationInventoryRepository.save(medInventory);

                // set the MedicationStorage with the new MedicationBlock
                medicationBlockRepository.save(prescriptionServiceMapper.toMedicationBlock(
                        medicationStorage,
                        prescriptionDTO,
                        recommendMedicationDTO
                ));

                // save data in recommendMedication table
                recommendMedicationRepository.save(prescriptionServiceMapper.toRecommendMedication(
                        savedPrescription,
                        medicationStorage,
                        recommendMedicationDTO
                ));
            }
        }

        return "Prescription with ID " + savedPrescription.getPrescriptionId() + " added successfully.";
    }

    // Method to send an alert to the user
    private String sendAlert(String message) {
        // Logic to send alert (e.g., sending an email, logging, etc.)
        // For now, we'll return the message as a simple alert
        System.out.println("ALERT: " + message);
        return "ALERT: " + message;
    }
}
