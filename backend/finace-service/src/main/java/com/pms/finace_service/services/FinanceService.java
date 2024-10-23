package com.pms.finace_service.services;

import com.pms.finace_service.dto.ExpensesList;
import com.pms.finace_service.dto.IncomeList;
import com.pms.finace_service.repository.ExpensesRepository;
import com.pms.finace_service.repository.IncomeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class FinanceService {
    private final IncomeRepository incomeRepository;
    private final ExpensesRepository expensesRepository;
    private final FinanceMapper mapper;

    // get all expenses
    public List<ExpensesList> getAllExpensesList() {
        return expensesRepository.findAllExpenses()
                .stream()
                .map(mapper::fromExpenses)
                .collect(Collectors.toList());
    }

    // get all expenses
    public List<IncomeList> getAllIncomeList() {
        return incomeRepository.findAllIncome()
                .stream()
                .map(mapper::fromIncome)
                .collect(Collectors.toList());
    }

    // all finance
    public List<FinanceList> getAllFinanceList() {
        List<ExpensesList> exList = getAllExpensesList();
        List<IncomeList> inList = getAllIncomeList();
        return Stream.concat(
                exList.stream().map(expense -> new FinanceList(
                        null, // income_id is null for expenses
                        expense.date(),
                        expense.expense_value(),
                        expense.description(),
                        expense.farm_code(),
                        expense.expense_type(),
                        "expense" // tag for expenses
                )),
                inList.stream().map(income -> new FinanceList(
                        income.income_id(),
                        income.date(),
                        income.income_value(),
                        income.description(),
                        income.farm_code(),
                        null, // expense_type is null for incomes
                        "income" // tag for incomes
                ))
        ).collect(Collectors.toList());
    }

}
