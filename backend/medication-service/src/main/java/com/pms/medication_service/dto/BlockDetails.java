package com.pms.medication_service.dto;

public record BlockDetails(
        Integer placement_id,
        String placement_code,
        double current_block_quantity
) {
}
