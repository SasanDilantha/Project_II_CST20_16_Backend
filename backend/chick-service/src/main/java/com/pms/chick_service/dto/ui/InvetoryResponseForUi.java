package com.pms.chick_service.dto.ui;

import java.math.BigDecimal;
import java.time.LocalDate;

public record InvetoryResponseForUi(
        Integer chick_inventory_id,
        String chick_inventory_code,
        Integer available_quantity,
        BigDecimal expense_value,
        String chick_breed_name,
        Integer age,
        String supplier_name,
        String supplier_mobile,
        LocalDate date
) {}
