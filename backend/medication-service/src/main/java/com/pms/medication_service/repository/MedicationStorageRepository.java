package com.pms.medication_service.repository;

import com.pms.medication_service.model.MedicationStorage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicationStorageRepository extends JpaRepository<MedicationStorage, Integer> {
}
