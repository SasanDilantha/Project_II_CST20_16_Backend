package com.pms.farm_service.repository;

import com.pms.farm_service.model.ChickMortality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ChickMortalityRepository extends JpaRepository<ChickMortality, Integer> {

    @Query("SELECT cm FROM ChickMortality cm WHERE cm.placement.placement_id = :placementId ORDER BY cm.mortality_date DESC LIMIT 1 ")
    ChickMortality findByPlacementId(@Param("placementId") Integer placementId);
}
