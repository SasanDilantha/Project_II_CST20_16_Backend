package com.pms.feed_service.services;

import com.pms.feed_service.dto.FeedInventoryRequest;
import com.pms.feed_service.model.FeedInventoryCost;
import com.pms.feed_service.model.FeedSupplier;
import com.pms.feed_service.repository.FeedSupplierRepository;
import org.springframework.stereotype.Service;

@Service
public class CostMapper {
    public FeedInventoryCost toCost(FeedInventoryRequest request, Integer supplierId, FeedSupplierRepository supplierRepository) {
        FeedSupplier supplier = supplierRepository.findById(supplierId)
                .orElseThrow(
                        () -> new IllegalArgumentException("Supplier not found with id: " + supplierId )
                );

        return FeedInventoryCost.builder()
                .feed_inventory_cost_id(request.feed_inventory_cost_id())
                .cost_per_unit(request.cost_per_unit())
                .feedSupplier(supplier)
                .build();
    }
}
