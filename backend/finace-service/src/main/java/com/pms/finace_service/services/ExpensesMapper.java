package com.pms.finace_service.services;

import com.pms.finace_service.dto.ExpenseRequest;
import com.pms.finace_service.model.Expenses;
import org.springframework.stereotype.Service;

@Service
public class ExpensesMapper {
    public Expenses toCheckInventry(ExpenseRequest request, String farm_code) {
        return Expenses.builder()
                .expense_value(request.total_price())
                .farm_code(farm_code)
                .build();
    }
}
