package com.pms.chick_service.repository;

import com.pms.chick_service.model.ChickInventryCost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChickInventryCostRepository extends JpaRepository<ChickInventryCost, Integer> {
}
