package com.pms.user_server.dto.client;

import java.math.BigDecimal;

public record ExpenseUserSalaryRequest(
        Integer expense_id,
        String description,
        String farm_code,
        BigDecimal salary
) {
}
