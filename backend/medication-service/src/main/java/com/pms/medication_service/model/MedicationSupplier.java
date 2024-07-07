package com.pms.medication_service.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "med_supplier")
public class MedicationSupplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer medication_supplier_id;
    private String supplier_name;
    private String supplier_phone;
    private String supplier_email;
    private String supplier_address;
    @OneToMany(mappedBy = "medicationSupplier", cascade = CascadeType.ALL)
    private List<MedicationInventoryCost> medication_inventory_cost;
}
