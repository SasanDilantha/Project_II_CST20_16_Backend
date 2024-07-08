package com.pms.feed_service.services;

import com.pms.feed_service.dto.FeedInventoryRequest;
import com.pms.feed_service.repository.FeedInventoryCostRepository;
import com.pms.feed_service.repository.FeedSupplierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryCostService {
    private final FeedInventoryCostRepository repository;
    private final  CostMapper  mapper;

    private final FeedSupplierService supplierService;
    private final FeedSupplierRepository supplierRepository;

    // create record of chick inventory request ----> save data to cost
    public Integer createInventoryCost(FeedInventoryRequest request){
        Integer supplier_id = supplierService.findSupplierId(request.supplier_name(),request.supplier_phone());
        var cost = repository.save(mapper.toCost(request,supplier_id,supplierRepository));
        return cost.getFeed_inventory_cost_id();
    }

}
