package com.pms.chick_service.dto;

public record BlockRequest(
        Integer block_id,
        Integer storage_id,
        String placement_code,
        Integer block_quantity

) {
}
