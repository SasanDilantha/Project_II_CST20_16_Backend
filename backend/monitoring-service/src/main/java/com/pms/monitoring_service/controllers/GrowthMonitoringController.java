package com.pms.monitoring_service.controllers;

import com.pms.monitoring_service.dto.MonitoringResponse;
import com.pms.monitoring_service.dto.SensorData;
import com.pms.monitoring_service.kafka.MonitoringNotifications;
import com.pms.monitoring_service.services.MonitoringService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/moni")
@RequiredArgsConstructor
public class GrowthMonitoringController {
    private final MonitoringService monitoringService;

    @GetMapping("/init/{placement-id}")
    public ResponseEntity<LocalDateTime> testInit(@PathVariable("placement-id") Integer placementId) {
        return ResponseEntity.ok(monitoringService.getChickStorageById(placementId));
    }

    @GetMapping("/get/all/data/{placement-id}")
    public ResponseEntity<MonitoringResponse> getLatestGrowthMonitoringRecord(@PathVariable("placement-id") Integer placementId){
        return ResponseEntity.ok(monitoringService.getLatestGrowthMonitoringRecord(placementId));
    }

    @GetMapping("/get/iot/data")
    public ResponseEntity<List<SensorData>> getSensorData() {
        return ResponseEntity.ok(monitoringService.getSensorData());
    }

    @PostMapping("/notify/test")
    public ResponseEntity<String> testNotify(@RequestBody MonitoringNotifications request) {
        monitoringService.testNotify(request);
        return ResponseEntity.ok("Test Notification sent successfully");
    }
}
