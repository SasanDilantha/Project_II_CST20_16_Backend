package com.pms.finace_service.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "income")
@EntityListeners(AuditingEntityListener.class)
public class Income {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer income_id;
    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime date;
    private BigDecimal income_value;
    private String description;
    private String farm_code;
    @Enumerated(EnumType.STRING)
    private Incomes income_type;
}
