package com.pms.medication_service.dto;


import com.pms.medication_service.model.MedicationType;

import java.math.BigDecimal;

public record MedicationInventoryRequest(
        Integer med_inventory_id,
        Integer med_storage_id,
        MedicationType med_type,
        double med_quantity,
        Integer med_inventory_cost_id,
        BigDecimal cost_per_unit,
        BigDecimal total_price,
        String supplier_name,
        String supplier_phone,
        Integer expense_id,
        String description,
        Integer farm_id
) { }
