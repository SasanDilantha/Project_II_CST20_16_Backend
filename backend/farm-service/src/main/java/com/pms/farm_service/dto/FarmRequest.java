package com.pms.farm_service.dto;

import com.pms.farm_service.model.ChickManure;
import com.pms.farm_service.model.Placement;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record FarmRequest(
        Integer farm_id,
        @NotNull(message = "Farm should have name")
        @NotNull(message = "Farm should have name")
        String farm_name,
        @NotEmpty(message = "Farm should have location")
        @NotEmpty(message = "Farm should have location")
        String farm_location,
        String farm_code
) { }
