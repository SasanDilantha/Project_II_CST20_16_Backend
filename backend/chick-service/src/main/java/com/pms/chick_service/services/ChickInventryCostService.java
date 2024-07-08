package com.pms.chick_service.services;

import com.pms.chick_service.dto.ChickInventoryRequest;
import com.pms.chick_service.repository.ChickInventryCostRepository;
import com.pms.chick_service.repository.ChickSupplierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChickInventryCostService {
    private final ChickInventryCostRepository repository;
    private final CostMapper mapper;
    private final ChickSupplierService chickSupplierService;
    private final ChickSupplierRepository supplierRepository;

    // create record of chick inventory request ----> save data to cost
    public Integer createInventryCost(ChickInventoryRequest request){
        Integer supplier_id = chickSupplierService.findSupplierId(request.supplier_name(),request.supplier_phone());
        var cost = repository.save(mapper.toCost(request,supplier_id,supplierRepository));
        return cost.getChick_inventry_cost_id();
    }
}
