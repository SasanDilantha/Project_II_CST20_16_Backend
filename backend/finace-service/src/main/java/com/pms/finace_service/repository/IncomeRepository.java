package com.pms.finace_service.repository;

import com.pms.finace_service.model.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Arrays;
import java.util.List;

public interface IncomeRepository extends JpaRepository<Income, Integer> {
    @Query("SELECT inc FROM Income inc ORDER BY inc.date DESC")
    List<Income> findAllIncome();
}
