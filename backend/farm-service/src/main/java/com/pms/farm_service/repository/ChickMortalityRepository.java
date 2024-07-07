package com.pms.farm_service.repository;

import com.pms.farm_service.model.ChickMortality;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChickMortalityRepository extends JpaRepository<ChickMortality, Integer> {
}
