package com.pms.farm_service.clients;

import com.pms.farm_service.dto.MortalityRequest;
import com.pms.farm_service.dto.ui.clients.FarmUiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(
        name = "chick-service",
        url = "http://localhost:8222/api/chick"
)
public interface ChickClient {
    @PutMapping("/update/mortality")
    Integer updateMortality(@RequestBody MortalityRequest request);

    // from get all farm relate details
    @GetMapping("/ui/get/farm")
    List<FarmUiResponse> forFarmDetails();
}
