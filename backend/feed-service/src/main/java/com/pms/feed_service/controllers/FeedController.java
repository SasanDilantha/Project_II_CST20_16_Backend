package com.pms.feed_service.controllers;

import com.pms.feed_service.dto.*;
import com.pms.feed_service.services.FeedInventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/feed")
@RequiredArgsConstructor
public class FeedController {
    private final FeedInventoryService feedService;

    // create or insert new feed inventory details
    @PostMapping
    public ResponseEntity<String> createChickInventory(@RequestBody FeedInventoryRequest request) {
        return ResponseEntity.ok(feedService.toInventory(request));
    }

    @GetMapping
    public ResponseEntity<List<InventoryResponse>> getAllInventory(){
        return ResponseEntity.ok(feedService.getAllInventory());
    }

    // get feed inventory details block wise
    @GetMapping("/inventory/{inventory-id}")
    public List<BlockDetails> getInventoryByUserId(@PathVariable("inventory-id") Integer inventoryId) {
        return feedService.getFeedInventoryDetails(inventoryId);
    }


    // for get all farm details for Farm Details UI
    @GetMapping("/ui/get/farm")
    public List<FarmUiResponse> forFarmDetails(){
        return feedService.forFarmDetails();
    }


    @PostMapping("/insert/block")
    public ResponseEntity<String> createBlock(@RequestBody BlockRequest request) {
        return ResponseEntity.ok(feedService.insertBlockDetails(request));
    }
}
