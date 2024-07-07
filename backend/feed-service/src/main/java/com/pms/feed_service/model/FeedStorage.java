package com.pms.feed_service.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "feed_storage")
@EntityListeners(AuditingEntityListener.class)
public class FeedStorage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer feed_id;
    @Enumerated(EnumType.STRING)
    private FeedType feed_type;
    private Integer initial_feed_quantity;
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime date;
    @OneToOne(mappedBy = "feedStorage", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private FeedInventory feedInventory;
    @OneToMany(mappedBy = "feed_storage", cascade = CascadeType.ALL)
    private List<FeedBlock> feedBlocks;
}
