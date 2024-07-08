package com.pms.feed_service.services;

import com.pms.feed_service.dto.*;
import com.pms.feed_service.dto.client.ExpenseRequest;
import com.pms.feed_service.model.FeedBlock;
import com.pms.feed_service.model.FeedInventory;
import org.springframework.stereotype.Service;

@Service
public class InventoryMapper {
    public ExpenseRequest toExpense(FeedInventoryRequest request) {
        return new ExpenseRequest(
                request.expense_id(),
                request.total_price(),
                request.description(),
                request.farm_id()
        );
    }

    public InventoryCode fromInventory(FeedInventory feedInventory) {
        return new InventoryCode(feedInventory.getFeed_inventory_code());
    }

    public InventoryResponse fromAllInventory(FeedInventory feedInventory) {
        return new InventoryResponse(
                feedInventory.getFeed_inventory_id(),
                feedInventory.getFeed_inventory_code(),
                feedInventory.getAvailable_quantity(),
                feedInventory.getExpense_id(),
                feedInventory.getFeedStorage().getFeed_id(),
                feedInventory.getFeedStorage().getFeed_type().toString(),
                feedInventory.getFeedInventoryCost().getCost_per_unit(),
                feedInventory.getFeedInventoryCost().getFeedSupplier().getSupplier_name(),
                feedInventory.getFeedInventoryCost().getFeedSupplier().getSupplier_phone()
        );
    }

    public FarmUiResponse forFarmUi(FeedInventory feedInventory) {
        return new FarmUiResponse(
               feedInventory.getFeed_inventory_id(),
               feedInventory.getFeedStorage().getInitial_feed_quantity(),
               feedInventory.getAvailable_quantity()
        );
    }

    public BlockDetails toBlockDetails(FeedBlock feedBlock, String placementCode) {
        return new BlockDetails(
                feedBlock.getPlacement_id(),
                placementCode,
                feedBlock.getBlock_quantity()
        );
    }
}
