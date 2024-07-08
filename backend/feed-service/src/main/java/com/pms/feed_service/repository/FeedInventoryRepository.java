package com.pms.feed_service.repository;

import com.pms.feed_service.model.FeedInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FeedInventoryRepository extends JpaRepository<FeedInventory, Integer> {

    @Query("SELECT fi.feedStorage.feed_id FROM FeedInventory fi WHERE fi.feed_inventory_id = :id ")
    Integer findStorageIdById(@Param("id") Integer chickInventoryId);
}
