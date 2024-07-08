package com.pms.medication_service.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "med_inventory_cost")
public class MedicationInventoryCost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer medication_inventory_cost_id;
    private BigDecimal cost_per_unit;
    @OneToOne(mappedBy = "medicationInventoryCost", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private MedicationInventory medicationInventory;
    @ManyToOne
    @JoinColumn(name = "medication_supllier_id")
    private MedicationSupplier medicationSupplier;
}
