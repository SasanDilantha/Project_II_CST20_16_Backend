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
@Table(name = "feed_inventry_cost")
public class FeedInevntoryCost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer feed_inventry_cost_id;
    private BigDecimal cost_per_unit;
    @OneToOne(mappedBy = "feedInventryCost", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private FeedInventory feedInventory;
    @ManyToOne
    @JoinColumn(name = "feed_supllier_id")
    private FeedSupplier feedSupplier;
}
