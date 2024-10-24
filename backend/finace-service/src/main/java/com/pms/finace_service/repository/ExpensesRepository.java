package com.pms.finace_service.repository;

import com.pms.finace_service.model.Expenses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ExpensesRepository extends JpaRepository<Expenses, Integer> {
    @Query("SELECT ex FROM Expenses ex WHERE ex.expense_type = 'SALARY'")
    List<Expenses> findAllByType();

    @Query("SELECT ex FROM Expenses ex ORDER BY ex.date DESC")
    List<Expenses> findAllExpenses();
}
