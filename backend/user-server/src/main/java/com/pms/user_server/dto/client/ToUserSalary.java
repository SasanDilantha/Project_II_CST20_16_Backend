package com.pms.user_server.dto.client;

import java.math.BigDecimal;

public record ToUserSalary(
        Integer expense_id,
        BigDecimal expense_value,
        String farm_code
) {
}
