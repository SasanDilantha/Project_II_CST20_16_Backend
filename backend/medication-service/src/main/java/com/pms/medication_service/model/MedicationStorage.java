package com.pms.medication_service.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "med_storage")
@EntityListeners(AuditingEntityListener.class)
public class MedicationStorage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer medication_id;
    @Enumerated(EnumType.STRING)
    private MedicationType medication_type;
    private double initial_medication_quantity;
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime date;
    @OneToOne(mappedBy = "medicationStorage", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private MedicationInventory medicationInventory;
    @OneToMany(mappedBy = "medication_storage", cascade = CascadeType.ALL)
    private List<MedicationBlock> medicationBlocks;
}
