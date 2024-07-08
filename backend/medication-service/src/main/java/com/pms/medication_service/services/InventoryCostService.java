package com.pms.medication_service.services;


import com.pms.medication_service.dto.MedicationInventoryRequest;
import com.pms.medication_service.repository.MedicationInventoryCostRepository;
import com.pms.medication_service.repository.MedicationSupplierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryCostService {
    private final MedicationInventoryCostRepository repository;
    private final  CostMapper  mapper;

    private final MeditationSupplierService supplierService;
    private final MedicationSupplierRepository supplierRepository;

    // create record of chick inventory request ----> save data to cost
    public Integer createInventoryCost(MedicationInventoryRequest request){
        Integer supplier_id = supplierService.findSupplierId(request.supplier_name(),request.supplier_phone());
        var cost = repository.save(mapper.toCost(request,supplier_id,supplierRepository));
        return cost.getMedication_inventory_cost_id();
    }

}
