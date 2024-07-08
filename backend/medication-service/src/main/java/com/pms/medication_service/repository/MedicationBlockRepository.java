package com.pms.medication_service.repository;

import com.pms.medication_service.model.MedicationBlock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicationBlockRepository extends JpaRepository<MedicationBlock, Integer> {
}
