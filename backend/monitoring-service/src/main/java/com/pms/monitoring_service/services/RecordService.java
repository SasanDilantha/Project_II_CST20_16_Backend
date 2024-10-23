package com.pms.monitoring_service.services;

import com.pms.monitoring_service.clients.ChickClient;
import com.pms.monitoring_service.clients.FarmClient;
import com.pms.monitoring_service.dto.DailyChickRecord;
import com.pms.monitoring_service.dto.MonitoringResponse;
import com.pms.monitoring_service.dto.ReportRequest;
import com.pms.monitoring_service.repository.ReportRepository;
import com.pms.monitoring_service.repository.WeightRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class RecordService {
    private final WeightRecordRepository repository;
    private final ChickClient chickClient;
    private final FarmClient farmClient;
    private final ReportRepository reportRepository;
    private final Mapper mapper;

    private final MonitoringService monitoringService;

    // save daily chick record (weight & feed consumption)
    public String saveDailyRecord(DailyChickRecord request) {
        // get inventory id
        Integer inventoryId = chickClient.getInventoryIdByPlacementId(request.placementId());
        if (inventoryId == null) {
            return "Invalid placement id";
        }

        var record = repository.save(
                mapper.toWeightRecord(request, inventoryId)
        );
        return "Record saved successfully";
    }

    public MonitoringResponse sendReportData(ReportRequest request) {
        // get inventory id
        Integer inventoryId = chickClient.getInventoryIdByPlacementId(request.placementId());
        LocalDate date = LocalDate.now();
        String reportCode = setReportCode();

        // save record in database
        reportRepository.save(mapper.toReport
                (
                        request,
                        inventoryId,
                        date,
                        reportCode
                )
        );

        return monitoringService.getLatestGrowthMonitoringRecord(request.placementId());
    }

    private String setReportCode(){
        String reportCode = farmClient.gentReportCode();
        String isAlreadyHave = reportRepository.haveSameCode(reportCode);
        if (!isAlreadyHave.isEmpty()){
            setReportCode();
        }
        return reportCode;
    }

    public String addRecord(ReportRequest request) {
        var report = reportRepository.findByReportCode(request.reportCode());
        // set new value for record in report
        report.setAdditionalRecord(request.record());

        // update table
        reportRepository.save(report);

        return "Your addition is recorded!";
    }
}
