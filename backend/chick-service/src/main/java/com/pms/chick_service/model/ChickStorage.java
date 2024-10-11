package com.pms.chick_service.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "chick_storage")
@EntityListeners(AuditingEntityListener.class)
public class ChickStorage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer chick_storage_id;
    @Enumerated(EnumType.STRING)
    private Breed breed;
    private Integer chick_quantity;
    private Float init_weight;
    private Integer age;
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime date;
    @Column(name = "selling_date")
    private LocalDate sellingDate;
    @OneToOne(mappedBy = "chickStorage", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private ChickInventory chickInventory;
    @OneToMany(mappedBy = "chick_storage", cascade = CascadeType.ALL)
    private List<ChickBlock> chickBlocks;
}
