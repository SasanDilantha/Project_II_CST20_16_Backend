package com.pms.monitoring_service.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(
        name = "farm-service",
        url = "http://localhost:8222/api/farm"
)
public interface FarmClient {
    @GetMapping("/gen/report/code")
    String gentReportCode();

    @GetMapping("/placement/farm-name/{placementId}")
    ResponseEntity<String> getFarmNameByPlacementId(@PathVariable("placementId") Integer placementId);
}