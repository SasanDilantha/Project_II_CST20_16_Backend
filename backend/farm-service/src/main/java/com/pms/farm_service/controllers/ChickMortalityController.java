package com.pms.farm_service.controllers;

import com.pms.farm_service.dto.ChickMortalityRequest;
import com.pms.farm_service.service.ChickMortalityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/farm/mortality")
@RequiredArgsConstructor
public class ChickMortalityController {
    private final ChickMortalityService service;

    @PostMapping
    public ResponseEntity<String> addChickMortality(@RequestBody ChickMortalityRequest request) {
        return ResponseEntity.ok(service.addChickMortality(request));
    }
}
