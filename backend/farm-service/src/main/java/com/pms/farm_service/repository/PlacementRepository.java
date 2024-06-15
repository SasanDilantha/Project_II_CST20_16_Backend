package com.pms.farm_service.repository;

import com.pms.farm_service.model.Placement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface PlacementRepository extends JpaRepository<Placement, Integer> {
    @Query("SELECT p.placement_id FROM Placement p WHERE p.placement_code = :placementCode")
    Integer findIdByCode(@Param("placementCode") String placementCode);
}
