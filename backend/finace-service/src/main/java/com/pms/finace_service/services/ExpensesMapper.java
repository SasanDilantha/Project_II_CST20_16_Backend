package com.pms.finace_service.services;

import com.pms.finace_service.dto.ExpenseRequest;
import com.pms.finace_service.dto.client.ToExpenseInventory;
import com.pms.finace_service.dto.client.ToUserSalary;
import com.pms.finace_service.dto.client.request.ExpenseUserSalaryRequest;
import com.pms.finace_service.model.Expense;
import com.pms.finace_service.model.Expenses;
import org.springframework.stereotype.Service;

@Service
public class ExpensesMapper {
    public Expenses toCheckInventory(ExpenseRequest request, String farm_code) {
        String type = "GET_CHICK";
        return Expenses.builder()
                .expense_id(request.expense_id())
                .expense_value(request.total_price())
                .description(request.description())
                .farm_code(farm_code)
                .expense_type(Expense.valueOf(type))
                .build();
    }

    public Expenses toFeedInventory(ExpenseRequest request, String farm_code) {
        String type = "GET_CHICK";
        return Expenses.builder()
                .expense_id(request.expense_id())
                .expense_value(request.total_price())
                .description(request.description())
                .farm_code(farm_code)
                .expense_type(Expense.valueOf(type))
                .build();
    }

    public Expenses toMeditationInventory(ExpenseRequest request, String farm_code) {
        String type = "GET_CHICK";
        return Expenses.builder()
                .expense_id(request.expense_id())
                .expense_value(request.total_price())
                .description(request.description())
                .farm_code(farm_code)
                .expense_type(Expense.valueOf(type))
                .build();
    }

    public Expenses toExpenseFromUser(ExpenseUserSalaryRequest request) {
        String type = "SALARY";
        return Expenses.builder()
                .expense_id(request.expense_id())
                .expense_value(request.salary())
                .description(request.description())
                .farm_code(request.farm_code())
                .expense_type(Expense.valueOf(type))
                .build();
    }

    public ToUserSalary toUserSalary(Expenses expenses) {
        return new ToUserSalary(
                expenses.getExpense_id(),
                expenses.getExpense_value(),
                expenses.getFarm_code()
        );
    }

    public ToExpenseInventory toExpenseInventory(Expenses expenses) {
        return new ToExpenseInventory(
                expenses.getDate(),
                expenses.getExpense_value()
        );
    }
}
