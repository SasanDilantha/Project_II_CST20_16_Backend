package com.pms.medication_service.controllers;

import com.pms.medication_service.dto.MedicationSupplierResponse;
import com.pms.medication_service.dto.MeditationSupplierRequest;
import com.pms.medication_service.services.MeditationSupplierService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/med/supplier")
@RequiredArgsConstructor
public class MeditationSupplierController {
    private final MeditationSupplierService service;

    @PostMapping
    public ResponseEntity<String> createSupplier(@RequestBody @Valid MeditationSupplierRequest request){
        return ResponseEntity.ok(service.createSupplier(request));
    }

    @GetMapping
    public ResponseEntity<List<MedicationSupplierResponse>> getAllSupplier(){
        return ResponseEntity.ok(service.getAllSupplier());
    }
}
