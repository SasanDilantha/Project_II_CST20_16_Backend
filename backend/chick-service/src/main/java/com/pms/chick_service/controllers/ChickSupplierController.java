package com.pms.chick_service.controllers;

import com.pms.chick_service.dto.ChickSupplierRequest;
import com.pms.chick_service.dto.ChickSupplierResponse;
import com.pms.chick_service.services.ChickSupplierService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chick/supplier")
@RequiredArgsConstructor
public class ChickSupplierController {
    private final ChickSupplierService service;

    @PostMapping
    public ResponseEntity<String> createSupplier(@RequestBody @Valid ChickSupplierRequest request){
        return ResponseEntity.ok(service.createSupplier(request));
    }

    @GetMapping
    public ResponseEntity<List<ChickSupplierResponse>> getAllSupplier(){
        return ResponseEntity.ok(service.getAllSupplier());
    }
}
