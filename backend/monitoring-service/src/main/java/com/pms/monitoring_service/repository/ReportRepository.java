package com.pms.monitoring_service.repository;

import com.pms.monitoring_service.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReportRepository extends JpaRepository<Report, Integer> {
    Report findByReportCode(String string);

    @Query("SELECT r.reportCode FROM Report r WHERE r.reportCode = :recordCode")
    String haveSameCode(@Param("recordCode") String recordCode);
}
