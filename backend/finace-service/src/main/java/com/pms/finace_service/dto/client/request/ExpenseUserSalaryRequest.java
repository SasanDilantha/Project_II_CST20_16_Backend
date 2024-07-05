package com.pms.finace_service.dto.client.request;

import java.math.BigDecimal;

public record ExpenseUserSalaryRequest(
        Integer expense_id,
        String description,
        String farm_code,
        BigDecimal salary
) {
}
