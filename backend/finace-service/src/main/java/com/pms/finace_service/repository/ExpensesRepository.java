package com.pms.finace_service.repository;

import com.pms.finace_service.model.Expenses;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpensesRepository extends JpaRepository<Expenses, Integer> {
}
