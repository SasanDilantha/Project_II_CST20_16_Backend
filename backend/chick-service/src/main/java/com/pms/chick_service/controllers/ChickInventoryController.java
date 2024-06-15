package com.pms.chick_service.controllers;

import com.pms.chick_service.dto.BlockRequest;
import com.pms.chick_service.dto.ChickInventoryRequest;
import com.pms.chick_service.dto.InvetoryResponse;
import com.pms.chick_service.dto.MortalityRequest;
import com.pms.chick_service.services.ChickInventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chick")
@RequiredArgsConstructor
public class ChickInventoryController {
    private final ChickInventoryService service;

    @PostMapping
    public ResponseEntity<String> createChickInventory(@RequestBody ChickInventoryRequest request) {
        return ResponseEntity.ok(service.toInventory(request));
    }

    @GetMapping
    public ResponseEntity<List<InvetoryResponse>> getAllInvetory(){
        return ResponseEntity.ok(service.getAllInvetory());
    }

    @PutMapping("/update/mortality")
    public Integer updateMortality(@RequestBody MortalityRequest request) {
        return service.updateAvailableChick(request);
    }

    @PostMapping("/create/block")
    public ResponseEntity<String> createBlock(@RequestBody BlockRequest request) {
        return ResponseEntity.ok(service.createBlock(request));
    }

}
