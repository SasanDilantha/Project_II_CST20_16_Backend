package com.pms.feed_service.repository;

import com.pms.feed_service.model.FeedSupplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FeedSupplierRepository extends JpaRepository<FeedSupplier, Integer> {

    @Query("SELECT f.feed_supplier_id FROM FeedSupplier f WHERE f.supplier_name = :supplierName AND f.supplier_phone = :supplierPhoneNumber")
    Integer findIdByNameAndPhoneNumber(@Param("supplierName") String supplierName, @Param("supplierPhoneNumber") String supplierPhoneNumber);
}
