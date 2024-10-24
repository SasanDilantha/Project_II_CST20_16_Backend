package com.pms.monitoring_service.services;

import com.pms.monitoring_service.dto.DailyChickRecord;
import com.pms.monitoring_service.dto.ReportRequest;
import com.pms.monitoring_service.model.Report;
import com.pms.monitoring_service.model.WeightRecord;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class Mapper {
    public WeightRecord toWeightRecord(DailyChickRecord request, Integer inventoryId) {
        LocalDate date = LocalDate.now();
        return WeightRecord.builder()
                .placementId(request.placementId())
                .weight(request.weight())
                .feedConsumption(request.feedConsumption())
                .date(date)
                .inventoryId(inventoryId)
                .build();
    }

    public Report toReport(ReportRequest request, Integer inventoryId, LocalDate date, String reportCode) {
        return Report.builder()
                .reportCode(reportCode)
                .date(date)
                .farmId(request.farmId())
                .inventoryId(inventoryId)
                .build();
    }
}
