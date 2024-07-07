package com.pms.medication_service.dto;

import java.math.BigDecimal;

public record InventoryResponse(
        Integer med_inventory_id,
        String med_inventory_code,
        double available_quantity,
        Integer expense_id,
        Integer med_storage_id,
        String med_type,
        BigDecimal cost_per_unit,
        String supplier_name,
        String supplier_mobile
) {
}
