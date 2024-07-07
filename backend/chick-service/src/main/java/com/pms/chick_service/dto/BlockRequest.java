package com.pms.chick_service.dto;

import com.pms.chick_service.model.Breed;

public record BlockRequest(
        Integer block_id,
        Integer breed_id,
        String placement_code,
        Integer block_quantity

) {
}
