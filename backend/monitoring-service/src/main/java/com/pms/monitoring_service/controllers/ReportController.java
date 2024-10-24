package com.pms.monitoring_service.controllers;

import com.pms.monitoring_service.dto.MonitoringResponse;
import com.pms.monitoring_service.dto.ReportRequest;
import com.pms.monitoring_service.services.RecordService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/moni/report")
@RequiredArgsConstructor
public class ReportController {
    private final RecordService service;

    @GetMapping("/get/all/data")
    public ResponseEntity<MonitoringResponse> getAllData(@RequestBody @Valid ReportRequest request){
        return ResponseEntity.ok(service.sendReportData(request));
    }

    @PutMapping("/set/addition/rec")
    public ResponseEntity<String> additionalRecord(@RequestBody @Valid ReportRequest request){
        return ResponseEntity.ok(service.addRecord(request));
    }
}
