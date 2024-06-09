package com.pms.farm_service.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "placement")
public class Placement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer placement_id;
    private String placement_code;
    @ManyToOne
    @JoinColumn(name = "farm_id")
    private Farm farm;
    @OneToMany(mappedBy = "placement", cascade = CascadeType.ALL)
    private List<BroilerSales> broilerSales;
    @OneToOne(mappedBy = "placement", cascade = CascadeType.ALL)
    private ChickMortality chickMortalities;
}
