package com.pms.farm_service.repository;

import com.pms.farm_service.model.Farm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FarmRepository extends JpaRepository<Farm, Integer> {
    @Query("SELECT f.farm_id FROM Farm f WHERE f.farm_code = :farmCode")
    Integer findIdByFarmCode(@Param("farmCode") String farmCode);
}
