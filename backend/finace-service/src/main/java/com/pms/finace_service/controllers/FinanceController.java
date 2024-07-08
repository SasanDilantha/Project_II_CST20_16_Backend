package com.pms.finace_service.controllers;

import com.pms.finace_service.dto.ExpensesList;
import com.pms.finace_service.dto.IncomeList;
import com.pms.finace_service.services.FinanceList;
import com.pms.finace_service.services.FinanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/fin")
@RequiredArgsConstructor
public class FinanceController {
    private final FinanceService service;

    @GetMapping("/income")
    public List<IncomeList> getAllIncome() {
        return service.getAllIncomeList();
    }

    @GetMapping("/expense")
    public List<ExpensesList> getAllExpense() {
        return service.getAllExpensesList();
    }

    @GetMapping("/all")
    public List<FinanceList> getAllFinance() {
        return service.getAllFinanceList();
    }

}
