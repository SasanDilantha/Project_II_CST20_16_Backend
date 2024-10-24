package com.pms.medication_service.dto.ui;

import java.math.BigDecimal;
import java.time.LocalDate;

public record InvetoryResponseForUi(
        Integer medication_inventory_id,
        String medication_inventory_code,
        double available_quantity,
        BigDecimal expense_value,
        String med_type,
        String supplier_name,
        String supplier_mobile,
        LocalDate date
) { }
