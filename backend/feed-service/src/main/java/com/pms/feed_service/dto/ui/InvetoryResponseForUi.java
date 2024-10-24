package com.pms.feed_service.dto.ui;

import java.math.BigDecimal;
import java.time.LocalDate;

public record InvetoryResponseForUi(
        Integer feed_inventory_id,
        String feed_inventory_code,
        double available_quantity,
        BigDecimal expense_value,
        String feed_type,
        String supplier_name,
        String supplier_mobile,
        LocalDate date
) { }
