package com.pms.medication_service.model;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "med_inventory")
public class MedicationInventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer medication_inventory_id;
    private String medication_inventory_code;
    private double available_quantity;
    private Integer expense_id;
    @OneToOne
    @JoinColumn(name = "medication_id")
    private MedicationStorage medicationStorage;
    @OneToOne
    @JoinColumn(name = "medication_inventory_cost_id")
    private MedicationInventoryCost medicationInventoryCost;
}
