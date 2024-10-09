package com.pms.monitoring_service.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class GrowthMonitoring {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer growth_monitoring_id;
    private LocalDate recordDate;
    private Double weightGain; // in kg
    private Double feedConversionRatio; // in kg

    @OneToMany(mappedBy = "growthMonitoring", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<WeightRecord> weightRecords;
}
