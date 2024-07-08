package com.pms.feed_service.services;

import com.pms.feed_service.dto.BlockRequest;
import com.pms.feed_service.dto.FeedInventoryRequest;
import com.pms.feed_service.model.FeedBlock;
import com.pms.feed_service.model.FeedInventory;
import com.pms.feed_service.model.FeedInventoryCost;
import com.pms.feed_service.model.FeedStorage;
import com.pms.feed_service.repository.FeedInventoryCostRepository;
import com.pms.feed_service.repository.FeedStorageRepository;
import org.springframework.stereotype.Service;

@Service
public class FeedMapper {
    public FeedStorage toStorage(FeedInventoryRequest request) {
        return FeedStorage.builder()
                .feed_id(request.feed_storage_id())
                .feed_type(request.feed_type())
                .initial_feed_quantity(request.feed_quantity())
                .build();
    }

    public FeedInventory toInventory(
            FeedInventoryRequest request,
            Integer feedId,
            Integer feedInventoryCostId,
            Integer expenseId,
            String feedInventoryCode,
            FeedStorageRepository storageRepository,
            FeedInventoryCostRepository inventoryCostRepository
    ) {
        FeedStorage storage = storageRepository.findById(feedId).orElseThrow(
                ()-> new IllegalArgumentException("Storage not found with id: " + feedId)
        );
        FeedInventoryCost cost = inventoryCostRepository.findById(feedInventoryCostId).orElseThrow(
                () -> new IllegalArgumentException("Cost not found with id: " + feedInventoryCostId)
        );
        return FeedInventory.builder()
                .feed_inventory_id(request.feed_inventory_id())
                .available_quantity(request.feed_quantity())
                .feedStorage(storage)
                .feed_inventory_code(feedInventoryCode)
                .feedInventoryCost(cost)
                .expense_id(expenseId)
                .build();
    }

    public FeedBlock toBlock(BlockRequest request, Integer placementId, FeedStorageRepository storageRepository) {
        FeedStorage storage = storageRepository.findById(request.storage_id()).orElseThrow(
                ()-> new IllegalArgumentException("storage not found with id: " + request.storage_id())
        );
        return FeedBlock.builder()
                .feed_block_id(request.block_id())
                .block_quantity(request.block_quantity())
                .feed_storage(storage)
                .placement_id(placementId)
                .build();
    }
}
