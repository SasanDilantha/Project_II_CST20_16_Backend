package com.pms.medication_service.repository;

import com.pms.medication_service.model.MedicationInventoryCost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicationInventoryCostRepository extends JpaRepository<MedicationInventoryCost, Integer> {
}
