package com.pms.chick_service.dto;

import com.pms.chick_service.model.Breed;

import java.math.BigDecimal;


public record ChickInventoryRequest(
        Integer chick_inventory_id,
        Integer chick_storage_id,
        Breed breed,
        Integer chick_quantity,
        Integer age,
        Integer chick_inventry_cost_id,
        BigDecimal price_per_chick,
        BigDecimal total_price,
        String supplier_name,
        String supplier_phone,
        Integer expense_id,
        String description,
        Integer farm_id
) {}
