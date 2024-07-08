package com.pms.medication_service.model;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "med_block")
public class MedicationBlock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer medication_block_id;
    private Integer placement_id;
    private double block_quantity;
    @ManyToOne
    @JoinColumn(name = "medication_id")
    private MedicationStorage medication_storage;
}
