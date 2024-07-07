package com.pms.farm_service.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "chick_mortality")
public class ChickMortality {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer mortality_id;
    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDate mortality_date;
    private Integer quantity;
    private String description;
    @ManyToOne
    @JoinColumn(name = "placement_id")
    private Placement placement;
}
