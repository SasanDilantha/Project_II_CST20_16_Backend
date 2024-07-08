package com.pms.finace_service.dto;


import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ExpensesList(
    Integer expense_id,
    LocalDateTime date,
    BigDecimal expense_value,
    String description,
    String farm_code,
    String expense_type
) {
}
