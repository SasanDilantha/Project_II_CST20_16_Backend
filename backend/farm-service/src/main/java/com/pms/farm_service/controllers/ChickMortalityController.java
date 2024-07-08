package com.pms.farm_service.controllers;

import com.pms.farm_service.dto.ChickMortalityRequest;
import com.pms.farm_service.dto.ui.response.ToChickBlockDetails;
import com.pms.farm_service.service.ChickMortalityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/farm/mortality")
@RequiredArgsConstructor
public class ChickMortalityController {
    private final ChickMortalityService service;

    @PostMapping
    public ResponseEntity<String> addChickMortality(@RequestBody ChickMortalityRequest request) {
        return ResponseEntity.ok(service.addChickMortality(request));
    }

    // from mortality count to chick service
    @GetMapping("/block/count/{placement-id} ")
    public ToChickBlockDetails getMortalityDetails(@PathVariable("placement-id") Integer placementId){
        return service.getMortalityDetails(placementId);
    }
}
