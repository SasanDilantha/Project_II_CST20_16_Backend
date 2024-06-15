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
@Table(name = "expenses")
@EntityListeners(AuditingEntityListener.class)
public class Expenses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer expense_id;
    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime date;
    private BigDecimal expense_value;
    private String description;
    private String farm_code;
}
