package com.pms.finace_service.services;

import com.pms.finace_service.dto.ExpensesList;
import com.pms.finace_service.dto.IncomeList;
import com.pms.finace_service.model.Expenses;
import com.pms.finace_service.model.Income;
import org.springframework.stereotype.Service;

@Service
public class FinanceMapper {
    public ExpensesList fromExpenses(Expenses expenses) {
        return new ExpensesList(
                expenses.getExpense_id(),
                expenses.getDate(),
                expenses.getExpense_value(),
                expenses.getDescription(),
                expenses.getFarm_code(),
                expenses.getExpense_type().toString()
        );
    }

    public IncomeList fromIncome(Income income) {
        return new IncomeList(
                income.getIncome_id(),
                income.getDate(),
                income.getIncome_value(),
                income.getDescription(),
                income.getFarm_code(),
                income.getIncome_type().toString()
        );
    }
}
