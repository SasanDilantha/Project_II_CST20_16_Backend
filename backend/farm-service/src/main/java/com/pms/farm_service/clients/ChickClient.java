package com.pms.farm_service.clients;

import com.pms.farm_service.dto.MortalityRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "chick-service",
        url = "http://localhost:8222/api/chick"
)
public interface ChickClient {
    @PutMapping("/update/mortality")
    Integer updateMortality(@RequestBody MortalityRequest request);
}
