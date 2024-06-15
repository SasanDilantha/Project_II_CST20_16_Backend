package com.pms.chick_service.repository;

import com.pms.chick_service.model.ChickStorage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChickStorageRepository extends JpaRepository<ChickStorage, Integer> {
}
