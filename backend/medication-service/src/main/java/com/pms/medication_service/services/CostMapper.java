package com.pms.medication_service.services;

import com.pms.medication_service.dto.MedicationInventoryRequest;
import com.pms.medication_service.model.MedicationInventoryCost;
import com.pms.medication_service.model.MedicationSupplier;
import com.pms.medication_service.repository.MedicationSupplierRepository;
import org.springframework.stereotype.Service;

@Service
public class CostMapper {
    public MedicationInventoryCost toCost(MedicationInventoryRequest request, Integer supplierId, MedicationSupplierRepository supplierRepository) {
        MedicationSupplier supplier = supplierRepository.findById(supplierId)
                .orElseThrow(
                        () -> new IllegalArgumentException("Supplier not found with id: " + supplierId )
                );

        return MedicationInventoryCost.builder()
                .medication_inventory_cost_id(request.med_inventory_cost_id())
                .cost_per_unit(request.cost_per_unit())
                .medicationSupplier(supplier)
                .build();
    }
}
