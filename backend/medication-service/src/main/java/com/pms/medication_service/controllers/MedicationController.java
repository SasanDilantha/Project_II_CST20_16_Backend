package com.pms.medication_service.controllers;

import com.pms.medication_service.dto.*;
import com.pms.medication_service.services.MeditationInventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/med")
@RequiredArgsConstructor
public class MedicationController {
    private final MeditationInventoryService service;

    // create or insert new feed inventory details
    @PostMapping
    public ResponseEntity<String> createChickInventory(@RequestBody MedicationInventoryRequest request) {
        return ResponseEntity.ok(service.toInventory(request));
    }

    @GetMapping
    public ResponseEntity<List<InventoryResponse>> getAllInventory(){
        return ResponseEntity.ok(service.getAllInventory());
    }

    // get feed inventory details block wise
    @GetMapping("/inventory/{inventory-id}")
    public List<BlockDetails> getInventoryByUserId(@PathVariable("inventory-id") Integer inventoryId) {
        return service.getFeedInventoryDetails(inventoryId);
    }


    // for get all farm details for Farm Details UI
    @GetMapping("/ui/get/farm")
    public List<FarmUiResponse> forFarmDetails(){
        return service.forFarmDetails();
    }


    @PostMapping("/insert/block")
    public ResponseEntity<String> createBlock(@RequestBody BlockRequest request) {
        return ResponseEntity.ok(service.insertBlockDetails(request));
    }
}
