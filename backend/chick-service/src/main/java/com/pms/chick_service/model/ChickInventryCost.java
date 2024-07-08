package com.pms.chick_service.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "chick_inventry_cost")
public class ChickInventryCost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer chick_inventry_cost_id;
    private BigDecimal price_per_chick;
    @OneToOne(mappedBy = "chickInventryCost", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private ChickInventory chickInventory;
    @ManyToOne
    @JoinColumn(name = "chick_supplier_id")
    private ChickSupplier chickSupplier;
}
