package com.pms.feed_service.controllers;

import com.pms.feed_service.dto.FeedSupplierRequest;
import com.pms.feed_service.dto.FeedSupplierResponse;
import com.pms.feed_service.services.FeedSupplierService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/feed/supplier")
@RequiredArgsConstructor
public class FeedSupplierController {
    private final FeedSupplierService service;

    @PostMapping
    public ResponseEntity<String> createSupplier(@RequestBody @Valid FeedSupplierRequest request){
        return ResponseEntity.ok(service.createSupplier(request));
    }

    @GetMapping
    public ResponseEntity<List<FeedSupplierResponse>> getAllSupplier(){
        return ResponseEntity.ok(service.getAllSupplier());
    }
}
