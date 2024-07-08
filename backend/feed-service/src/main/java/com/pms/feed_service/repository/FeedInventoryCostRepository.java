package com.pms.feed_service.repository;

import com.pms.feed_service.model.FeedInventoryCost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedInventoryCostRepository extends JpaRepository<FeedInventoryCost, Integer> {
}
