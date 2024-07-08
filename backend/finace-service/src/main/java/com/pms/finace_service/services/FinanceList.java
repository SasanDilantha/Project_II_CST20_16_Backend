package com.pms.finace_service.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record FinanceList (
        Integer income_id,
        LocalDateTime date,
        BigDecimal income_value,
        String description,
        String farm_code,
        String expense_type,
        String tag
) {
}
