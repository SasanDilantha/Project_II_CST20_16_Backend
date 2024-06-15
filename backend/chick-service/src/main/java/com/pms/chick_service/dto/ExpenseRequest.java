package com.pms.chick_service.dto;

import java.math.BigDecimal;

public record ExpenseRequest(
        Integer expense_id,
        BigDecimal total_price,
        String description,
        Integer farm_id
) {
}
