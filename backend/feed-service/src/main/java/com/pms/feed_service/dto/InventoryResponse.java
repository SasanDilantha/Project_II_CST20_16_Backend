package com.pms.feed_service.dto;

import java.math.BigDecimal;

public record InventoryResponse(
        Integer feed_inventory_id,
        String feed_inventory_code,
        double available_quantity,
        Integer expense_id,
        Integer feed_storage_id,
        String feed_type,
        BigDecimal cost_per_unit,
        String supplier_name,
        String supplier_mobile
) {
}
