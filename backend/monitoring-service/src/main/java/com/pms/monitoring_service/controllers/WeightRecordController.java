package com.pms.monitoring_service.controllers;

import com.pms.monitoring_service.dto.DailyChickRecord;
import com.pms.monitoring_service.services.RecordService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/moni/weight")
@RequiredArgsConstructor
public class WeightRecordController {
    private final RecordService service;

    @PostMapping("/add/data")
    public ResponseEntity<String> setDailyChickWeight(@RequestBody @Valid DailyChickRecord request) {
        return ResponseEntity.ok(service.saveDailyRecord(request));
    }
}
