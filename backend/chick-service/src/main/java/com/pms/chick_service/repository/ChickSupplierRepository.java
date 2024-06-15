package com.pms.chick_service.repository;

import com.pms.chick_service.model.ChickSupplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ChickSupplierRepository extends JpaRepository<ChickSupplier, Integer> {

    @Query("SELECT c.chick_supplier_id FROM ChickSupplier c WHERE c.supplier_name = :supplierName AND c.supplier_phone = :supplierPhoneNumber")
    Integer findIdByNameAndPhoneNumber(@Param("supplierName") String supplierName, @Param("supplierPhoneNumber") String supplierPhoneNumber);
}
