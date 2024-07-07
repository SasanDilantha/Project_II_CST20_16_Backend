package com.pms.chick_service.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "chick_supplier")
public class ChickSupplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer chick_supplier_id;
    private String supplier_name;
    private String supplier_phone;
    private String supplier_email;
    private String supplier_address;
    @OneToMany(mappedBy = "chickSupplier", cascade = CascadeType.ALL)
    private List<ChickInventryCost> chick_inventry_costs;
}
