package com.pms.feed_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "farm-service",
        url = "http://localhost:8222/api/farm"
)
public interface FarmClient {
    @GetMapping("/placement/code/{placement-code}")
    Integer findPlacementIdByCode(@PathVariable("placement-code") String placementCode);

    @GetMapping("/placement/id/{placement-id}")
    String findPlacementCodeById(@PathVariable("placement-id") Integer placementId);

}
