package com.pms.chick_service.dto.client;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ToExpenseInventory(
        LocalDateTime date,
        BigDecimal expense_value
) {}
