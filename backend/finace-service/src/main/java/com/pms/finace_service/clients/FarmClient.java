package com.pms.finace_service.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "farm-service",
        url = "http://localhost:8222/api/farm"
)
public interface FarmClient {
    @GetMapping(value = "/id/{farm-id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    String getFarmCodeByFarmId(@PathVariable("farm-id")  Integer farmId);
}
