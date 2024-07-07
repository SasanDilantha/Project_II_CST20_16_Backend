package com.pms.feed_service.dto;

import com.pms.feed_service.model.FeedType;

import java.math.BigDecimal;

public record FeedInventoryRequest(
        Integer feed_inventory_id,
        Integer feed_storage_id,
        FeedType feed_type,
        double feed_quantity,
        Integer feed_inventory_cost_id,
        BigDecimal cost_per_unit,
        BigDecimal total_price,
        String supplier_name,
        String supplier_phone,
        Integer expense_id,
        String description,
        Integer farm_id
) { }
