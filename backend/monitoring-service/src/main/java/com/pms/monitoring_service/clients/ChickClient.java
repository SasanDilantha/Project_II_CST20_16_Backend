package com.pms.monitoring_service.clients;

import com.pms.monitoring_service.dto.FromChickMortality;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;

@FeignClient(
        name = "chick-service",
        url = "http://localhost:8222/api/chick"
)
public interface ChickClient {
    @GetMapping("/init/date/{placement-id}")
    LocalDateTime getChickStorageById(@PathVariable("placement-id") Integer placementId);

    @GetMapping("/mortality/data/{placement-id}")
    FromChickMortality getMortalityDetailsById(@PathVariable("placement-id") Integer placementId);

    @GetMapping("/init/weight/{placement-id}")
    Float getInitWeight(@PathVariable("placement-id") Integer placementId);

    @GetMapping("/get/inventory/{placement-id}")
    Integer getInventoryIdByPlacementId(@PathVariable("placement-id") Integer placementId);
}