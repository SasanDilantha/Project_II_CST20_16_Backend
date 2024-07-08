package com.pms.farm_service.model;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "chick_manure")
public class ChickManure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer chick_manure_sell_id;
    private Integer income_id;
    private double quantity;
    @ManyToOne
    @JoinColumn(name = "farm_id")
    private Farm farm;
}
