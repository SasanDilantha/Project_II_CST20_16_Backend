package com.pms.farm_service.dto;

public record ToUserDetails(
        Integer farm_id,
        String farm_name,
        String farm_code
) { }
