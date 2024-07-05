package com.pms.chick_service.model;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "chick_block")
public class ChickBlock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer chick_block_id;
    private Integer placement_id;
    private Integer block_quantity;
    @ManyToOne
    @JoinColumn(name = "chick_storage_id")
    private ChickStorage chick_storage;
}
