package com.pms.chick_service.dto;

import java.math.BigDecimal;

public record InvetoryResponse(
        Integer chick_inventory_id,
        String chick_inventory_code,
        Integer available_quantity,
        Integer expense_id,
        Integer chick_storage_id,
        String chick_breed_name,
        Integer age,
        BigDecimal price_per_chick,
        String supplier_name,
        String supplier_mobile
) {}
