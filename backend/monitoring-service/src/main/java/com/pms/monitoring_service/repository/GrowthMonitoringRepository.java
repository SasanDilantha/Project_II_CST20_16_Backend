package com.pms.monitoring_service.repository;

import com.pms.monitoring_service.model.GrowthMonitoring;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GrowthMonitoringRepository extends JpaRepository<GrowthMonitoring, Integer> {
    // JPQL to find the latest GrowthMonitoring record based on placementId
    @Query("SELECT gm FROM GrowthMonitoring gm " +
            "JOIN gm.weightRecords wr " +
            "WHERE wr.placementId = :placementId AND wr.inventoryId = :inventoryId " +
            "ORDER BY gm.recordDate DESC")
    GrowthMonitoring findLatestByPlacementId(@Param("placementId") Integer placementId, @Param("inventoryId") Integer inventoryId);
}
