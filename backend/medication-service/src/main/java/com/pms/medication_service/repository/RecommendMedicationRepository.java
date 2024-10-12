package com.pms.medication_service.repository;

import com.pms.medication_service.model.prescription.RecommendMedication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecommendMedicationRepository extends JpaRepository<RecommendMedication, Integer> {
}
