package com.pms.medication_service.repository;

import com.pms.medication_service.model.MedicationSupplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MedicationSupplierRepository extends JpaRepository<MedicationSupplier, Integer> {

    @Query("SELECT m.medication_supplier_id FROM MedicationSupplier m WHERE m.supplier_name = :supplierName AND m.supplier_phone = :supplierPhoneNumber")
    Integer findIdByNameAndPhoneNumber(@Param("supplierName") String supplierName, @Param("supplierPhoneNumber") String supplierPhoneNumber);
}
