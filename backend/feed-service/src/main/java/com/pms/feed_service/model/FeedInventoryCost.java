package com.pms.feed_service.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "feed_inventory_cost")
public class FeedInventoryCost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer feed_inventory_cost_id;
    private BigDecimal cost_per_unit;
    @OneToOne(mappedBy = "feedInventoryCost", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private FeedInventory feedInventory;
    @ManyToOne
    @JoinColumn(name = "feed_supllier_id")
    private FeedSupplier feedSupplier;
}
