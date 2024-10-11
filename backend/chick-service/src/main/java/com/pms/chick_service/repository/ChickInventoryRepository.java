package com.pms.chick_service.repository;

import com.pms.chick_service.model.ChickInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ChickInventoryRepository extends JpaRepository<ChickInventory, Integer> {
    @Modifying
    @Query("UPDATE ChickInventory ci SET ci.available_quantity = ci.available_quantity - :quantity WHERE ci.chickStorage.chick_storage_id =(" +
            "SELECT cb.chick_storage.chick_storage_id FROM ChickBlock cb WHERE cb.placement_id = :placementId)")
    int updateAvailableQuantity(@Param("quantity") int quantity, @Param("placementId") int placementId);

    @Query("SELECT ci FROM ChickInventory ci WHERE ci.chickStorage.chick_storage_id =  (" +
            "SELECT cb.chick_storage.chick_storage_id FROM ChickBlock cb WHERE cb.placement_id = :placementId)")
    Optional<ChickInventory> findAvailableQuantityByPlacementId(@Param("placementId") Integer placementId);

    @Modifying
    @Query("UPDATE ChickBlock cb SET cb.block_quantity = cb.block_quantity - :quantity WHERE cb.chick_storage.chick_storage_id = :chickBreedId AND cb.placement_id = :placementId")
    int updateBockQuantity(@Param("placementId") Integer placementId, @Param("quantity") Integer quantity ,@Param("chickBreedId") Integer chickBreedId);

    @Query("SELECT ci.chickStorage.chick_storage_id FROM ChickInventory ci WHERE ci.chick_inventory_id = :id ")
    Integer findStorageIdById(@Param("id") Integer chickInventoryId);

    @Query("SELECT ci.chick_inventory_id FROM ChickInventory ci " +
            "JOIN ci.chickStorage cs " +
            "JOIN cs.chickBlocks cb " +
            "WHERE cb.placement_id = :placementId " +
            "ORDER BY ci.chick_inventory_id DESC LIMIT 1")
    Integer findLatestChickInventoryIdByPlacementId(@Param("placementId") Integer placementId);
}
