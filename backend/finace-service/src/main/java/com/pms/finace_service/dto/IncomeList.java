package com.pms.finace_service.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record IncomeList(
        Integer income_id,
        LocalDateTime date,
        BigDecimal income_value,
        String description,
        String farm_code,
        String expense_type
) {
}
