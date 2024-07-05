package com.pms.feed_service.model;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "feed_block")
public class FeedBlock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer feed_block_id;
    private Integer placement_id;
    private Integer block_quantity;
    @ManyToOne
    @JoinColumn(name = "feed_id")
    private FeedStorage feed_storage;
}
