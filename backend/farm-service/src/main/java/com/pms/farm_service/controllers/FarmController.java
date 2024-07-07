package com.pms.farm_service.controllers;

import com.pms.farm_service.dto.FarmRequest;
import com.pms.farm_service.dto.FarmResponse;
import com.pms.farm_service.service.FarmService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/farm")
@RequiredArgsConstructor
public class FarmController {
    private final FarmService service;

    // insert farm details
    @PostMapping
    public ResponseEntity<String> addFarm(@RequestBody  @Valid FarmRequest request) {
        return ResponseEntity.ok(service.addFarm(request));
    }

    // get all farm details
    @GetMapping
    public ResponseEntity<List<FarmResponse>> getAllFarms(){
        return ResponseEntity.ok(service.getAllFarms());
    }

    // get farm Id  related farm code
    @GetMapping("/code/{farm-code}")
    public ResponseEntity<Integer> getFarmIdByFarmCode(@PathVariable("farm-code")  String farmCode){
        return ResponseEntity.ok(service.getFarmIdByFarmCode(farmCode));
    }

    // get farm code related farm id
    @GetMapping("/id/{farm-id}")
    public String getFarmCodeByFarmId(@PathVariable("farm-id")  Integer farmId){
        return service.getFarmCodeByFarmId(farmId);
    }
}
