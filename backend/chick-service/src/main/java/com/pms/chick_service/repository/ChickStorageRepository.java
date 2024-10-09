package com.pms.chick_service.repository;

import com.pms.chick_service.dto.StorageTest;
import com.pms.chick_service.model.ChickStorage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ChickStorageRepository extends JpaRepository<ChickStorage, Integer> {
    @Query("SELECT c FROM ChickStorage c WHERE c.sellingDate IS NULL")
    List<ChickStorage> findBySellingDateIsNull();

    @Query("SELECT cb.chick_storage.date FROM ChickBlock cb JOIN cb.chick_storage cs WHERE cb.placement_id = :placementId")
    LocalDateTime findStorageDateByPlacementId(@Param("placementId") Integer placementId);

    @Query("SELECT cb.chick_storage.chick_quantity FROM ChickBlock cb " +
            "JOIN cb.chick_storage cs " +
            "JOIN cs.chickInventory ci " +
            "WHERE cb.placement_id = :placementId AND ci.chick_inventory_id = :inventoryId")
    Integer findInitChickCountByPlacementId(@Param("placementId") Integer placementId, @Param("inventoryId") Integer inventoryId);

    @Query("SELECT cb.chick_storage.init_weight FROM ChickBlock cb " +
            "JOIN cb.chick_storage cs " +
            "JOIN cs.chickInventory ci " +
            "WHERE cb.placement_id = :placementId AND ci.chick_inventory_id = :inventoryId")
    Float findInitWeightByPlacementId(@Param("placementId") Integer placementId, @Param("inventoryId") Integer inventoryId);
}
