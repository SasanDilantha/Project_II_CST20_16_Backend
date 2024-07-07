package com.pms.chick_service.services;

import com.pms.chick_service.dto.ChickInventoryRequest;
import com.pms.chick_service.model.ChickInventryCost;
import com.pms.chick_service.model.ChickSupplier;
import com.pms.chick_service.repository.ChickSupplierRepository;
import org.springframework.stereotype.Service;

@Service
public class CostMapper {
    public ChickInventryCost toCost(ChickInventoryRequest request, Integer supplierId, ChickSupplierRepository supplierRepository) {
        ChickSupplier supplier = supplierRepository.findById(supplierId)
                .orElseThrow(
                        () -> new IllegalArgumentException("Supplier not found with id: " + supplierId )
                );

        return ChickInventryCost.builder()
                .chick_inventry_cost_id(request.chick_inventry_cost_id())
                .price_per_chick(request.price_per_chick())
                .chickSupplier(supplier)
                .build();
    }
}
