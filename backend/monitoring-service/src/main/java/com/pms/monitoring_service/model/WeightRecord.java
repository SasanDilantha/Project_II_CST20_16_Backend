package com.pms.monitoring_service.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class WeightRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer placementId;
    private Integer inventoryId;
    private Double weight;
    private LocalDate date;
    private Double feedConsumption; // in kg

    @ManyToOne
    @JoinColumn(name = "growth_monitoring_id")
    private GrowthMonitoring growthMonitoring;
}
