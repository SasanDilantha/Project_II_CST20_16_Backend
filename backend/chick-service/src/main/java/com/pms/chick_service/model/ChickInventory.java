package com.pms.chick_service.model;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "chick_inventory")
public class ChickInventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer chick_inventory_id;
    private String chick_inventory_code;
    private Integer available_quantity;
    private Integer expense_id;
    @OneToOne
    @JoinColumn(name = "chick_storage_id")
    private ChickStorage chickStorage;
    @OneToOne
    @JoinColumn(name = "chick_inventry_cost_id")
    private ChickInventryCost chickInventryCost;
}
