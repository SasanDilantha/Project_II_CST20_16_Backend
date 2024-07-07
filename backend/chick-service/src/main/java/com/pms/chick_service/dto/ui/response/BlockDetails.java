package com.pms.chick_service.dto.ui.response;

import java.util.Date;

public record BlockDetails(
        Integer placement_id,
        String placement_code,
        Integer current_count,
        Integer mortality_count,
        String description
) {
}
