package com.pms.feed_service.model;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "feed_inventory")
public class FeedInventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer feed_inventory_id;
    private String feed_inventory_code;
    private double available_quantity;
    private Integer expense_id;
    @OneToOne
    @JoinColumn(name = "feed_id")
    private FeedStorage feedStorage;
    @OneToOne
    @JoinColumn(name = "feed_inventory_cost_id")
    private FeedInventoryCost feedInventoryCost;
}
