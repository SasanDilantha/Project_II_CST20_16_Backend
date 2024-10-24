package com.pms.farm_service.dto.ui.response;

public record ReportUi(
        String farmName,
        Integer totalChicks,
        Integer healthyChicks,
        Integer sickChicks
) {
}
