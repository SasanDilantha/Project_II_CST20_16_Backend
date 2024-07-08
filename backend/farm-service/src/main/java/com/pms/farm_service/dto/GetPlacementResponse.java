package com.pms.farm_service.dto;

public record GetPlacementResponse(
       Integer placement_id,
       String placement_code,
       Integer farm_id
) {
}
