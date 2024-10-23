package com.pms.farm_service.controllers;

import com.pms.farm_service.dto.GetPlacementResponse;
import com.pms.farm_service.dto.PlacementRequest;
import com.pms.farm_service.dto.PlacementResponse;
import com.pms.farm_service.service.PlacementService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/farm/placement")
@RequiredArgsConstructor
public class PlacementController {
    private final PlacementService service;

    // create placement
    // this only pass farm id and number of placement
    @PostMapping
    public ResponseEntity<List<PlacementResponse>> createPlacement(@RequestBody @Valid PlacementRequest request){
        return  ResponseEntity.ok(service.createPlacement(request));
    }

    // get all placement details
    @GetMapping
    public ResponseEntity<List<GetPlacementResponse>> getPlacements(){
        return ResponseEntity.ok(service.getPlacements());
    }

    // get placement id by code
    @GetMapping("/code/{placementCode}")
    public Integer findPlacementIdByCode(@PathVariable("placementCode") String placementCode){
        return service.getPlacementIdByPlacementCode(placementCode);
    }

    // get placement code by using id
    @GetMapping("/id/{placement-id}")
    public String findPlacementCodeById(@PathVariable("placement-id") Integer placementId){
        return service.getPlacementCodeById(placementId);
    }
}
