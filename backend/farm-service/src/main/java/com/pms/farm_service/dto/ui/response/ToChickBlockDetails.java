package com.pms.farm_service.dto.ui.response;

import java.time.LocalDate;


public record ToChickBlockDetails(
        Integer count,
        String description,
        LocalDate date
) {
}
