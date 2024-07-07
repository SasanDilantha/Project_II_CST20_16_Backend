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
@Table(name = "farm")
public class Farm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer farm_id;
    private String farm_name;
    private String farm_location;
    private String farm_code;
    @OneToMany(mappedBy = "farm", cascade = CascadeType.ALL)
    private List<Placement> placements;
    @OneToMany(mappedBy = "farm", cascade = CascadeType.ALL)
    private List<ChickManure> chickManures;
}
