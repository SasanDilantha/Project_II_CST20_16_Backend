package com.pms.feed_service.repository;

import com.pms.feed_service.model.FeedStorage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedStorageRepository extends JpaRepository<FeedStorage, Integer> {
}
