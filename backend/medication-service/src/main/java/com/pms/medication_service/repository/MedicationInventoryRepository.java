package com.pms.medication_service.repository;

import com.pms.medication_service.model.MedicationInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MedicationInventoryRepository extends JpaRepository<MedicationInventory, Integer> {

    @Query("SELECT mi.medicationStorage.medication_id FROM MedicationInventory mi WHERE mi.medication_inventory_id = :id ")
    Integer findStorageIdById(@Param("id") Integer chickInventoryId);
}
