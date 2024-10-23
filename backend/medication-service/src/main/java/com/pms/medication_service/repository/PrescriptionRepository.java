package com.pms.medication_service.repository;

import com.pms.medication_service.model.prescription.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrescriptionRepository extends JpaRepository<Prescription, Integer> {
}
