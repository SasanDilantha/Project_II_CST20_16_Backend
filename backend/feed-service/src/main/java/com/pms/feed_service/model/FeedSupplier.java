package com.pms.feed_service.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "feed_supplier")
public class FeedSupplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer feed_supplier_id;
    private String supplier_name;
    private String supplier_phone;
    private String supplier_email;
    private String supplier_address;
    @OneToMany(mappedBy = "feedSupplier", cascade = CascadeType.ALL)
    private List<FeedInventoryCost> feed_inventry_costs;
}
