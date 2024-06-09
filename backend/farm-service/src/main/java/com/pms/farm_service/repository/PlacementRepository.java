package com.pms.farm_service.repository;

import com.pms.farm_service.model.Placement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlacementRepository extends JpaRepository<Placement, Integer> {
}
