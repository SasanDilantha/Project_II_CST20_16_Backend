package com.pms.monitoring_service.repository;

import com.pms.monitoring_service.model.WeightRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface WeightRecordRepository extends JpaRepository<WeightRecord, Integer> {
    List<WeightRecord> findByPlacementIdAndDateAfter(Integer placementId, LocalDate date);

    // Find weight records for a placement within a date range (last 3 days + today)
    List<WeightRecord> findByPlacementIdAndDateBetween(Integer placementId, LocalDate startDate, LocalDate endDate);

    // Find distinct placement IDs for monitoring
    @Query("SELECT DISTINCT wr.placementId FROM WeightRecord wr")
    List<Integer> findDistinctPlacementIds();

    List<WeightRecord> findByPlacementId(Integer placementId);
}
