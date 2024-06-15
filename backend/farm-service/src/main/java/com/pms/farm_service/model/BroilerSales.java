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
@Table(name = "broiler_sales")
public class BroilerSales {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer broiler_sale_id;
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDate sale_date;
    private double quantity;
    private Integer income_id;
    @ManyToOne
    @JoinColumn(name = "placement_id")
    private Placement placement;
}
