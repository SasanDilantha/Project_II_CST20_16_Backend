package com.pms.chick_service.dto.client;

import java.time.LocalDate;

public record ToChickBlockDetails(
        Integer count,
        String description,
        LocalDate date
) {
}
