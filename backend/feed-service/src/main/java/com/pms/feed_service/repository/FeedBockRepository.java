package com.pms.feed_service.repository;

import com.pms.feed_service.model.FeedBlock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedBockRepository extends JpaRepository<FeedBlock, Integer> {
}
