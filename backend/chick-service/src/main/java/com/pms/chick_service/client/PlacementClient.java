package com.pms.chick_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "farm-service",
        url = "http://localhost:8222/api/farm/placement"
)
public interface PlacementClient {
    @GetMapping("/code/{placement-code}")
    Integer findPlacementIdByCode(@PathVariable("placement-code") String placementCode);
}
