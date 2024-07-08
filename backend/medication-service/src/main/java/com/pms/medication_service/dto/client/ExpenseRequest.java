package com.pms.medication_service.dto.client;

import java.math.BigDecimal;

public record ExpenseRequest(
        Integer expense_id,
        BigDecimal total_price,
        String description,
        Integer farm_id
) {
}
