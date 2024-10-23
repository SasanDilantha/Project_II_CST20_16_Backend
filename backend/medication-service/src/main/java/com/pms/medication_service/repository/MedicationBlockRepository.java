package com.pms.medication_service.repository;

import com.pms.medication_service.model.MedicationBlock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MedicationBlockRepository extends JpaRepository<MedicationBlock, Integer> {
    // Query to find the latest MedicationBlock by placementId, based on Prescription date
    @Query("SELECT mb FROM MedicationBlock mb " +
            "JOIN mb.medication_storage ms " +
            "JOIN RecommendMedication rm ON rm.medicationStorage.medication_id = ms.medication_id " +
            "JOIN Prescription p ON p.prescriptionId = rm.prescription.prescriptionId " +
            "WHERE mb.placement_id = :placementId " +
            "ORDER BY p.date DESC")
    Optional<MedicationBlock> findLatestByPlacementId(Integer placementId);
}
