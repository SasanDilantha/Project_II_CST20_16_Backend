package com.pms.chick_service.controllers;

import com.pms.chick_service.dto.*;
import com.pms.chick_service.dto.client.FromChickMortality;
import com.pms.chick_service.dto.client.ToChickBlockDetails;
import com.pms.chick_service.dto.ui.response.BlockDetails;
import com.pms.chick_service.dto.ui.response.FarmUiResponse;
import com.pms.chick_service.model.ChickBlock;
import com.pms.chick_service.model.ChickStorage;
import com.pms.chick_service.services.ChickBlockTest;
import com.pms.chick_service.services.ChickInventoryService;
import com.pms.chick_service.services.ChickStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/chick")
@RequiredArgsConstructor
public class ChickInventoryController {
    private final ChickInventoryService service;
    private final ChickStorageService storageService;

    @PostMapping
    public ResponseEntity<String> createChickInventory(@RequestBody ChickInventoryRequest request) {
        return ResponseEntity.ok(service.toInventory(request));
    }

    @GetMapping
    public ResponseEntity<List<InvetoryResponse>> getAllInventory(){
        return ResponseEntity.ok(service.getAllInventory());
    }

    // get chick inventory details block wise
    @GetMapping("/inventory/{inventory-id}")
    public List<BlockDetails> getInventoryByUserId(@PathVariable("inventory-id") Integer inventoryId) {
        return service.getChickInventoryDetails(inventoryId);
    }


    // for get all farm details for Farm Details UI
    @GetMapping("/ui/get/farm")
    public List<FarmUiResponse> forFarmDetails(){
        return service.forFarmDetails();
    }

    @PutMapping("/update/mortality")
    public Integer updateMortality(@RequestBody MortalityRequest request) {
        return service.updateAvailableChick(request);
    }

    @PostMapping("/create/block")
    public ResponseEntity<String> createBlock(@RequestBody BlockRequest request) {
        return ResponseEntity.ok(service.createBlock(request));
    }

    @GetMapping("/init/date/{placement-id}")
    public LocalDateTime getChickStorageById(@PathVariable("placement-id") Integer placementId){
        return storageService.getChickStorageInitById(placementId);
    }

    @GetMapping("/mortality/data/{placement-id}")
    public FromChickMortality getMortalityDetailsById(@PathVariable("placement-id") Integer placementId){
        return storageService.getMortalityDetails(placementId);
    }

    @GetMapping("/init/weight/{placement-id}")
    public Float getInitWeight(@PathVariable("placement-id") Integer placementId){
        return storageService.testChickStorageService(placementId);
    }

    @GetMapping("/get/inventory/{placement-id}")
    public Integer getInventoryIdByPlacementId(@PathVariable("placement-id") Integer placementId){
        return service.getInventoryIdByPlacementId(placementId);
    }

}
