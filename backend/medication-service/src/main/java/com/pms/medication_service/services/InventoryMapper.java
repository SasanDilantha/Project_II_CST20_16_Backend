package com.pms.medication_service.services;

import com.pms.medication_service.dto.*;
import com.pms.medication_service.dto.client.ExpenseRequest;
import com.pms.medication_service.model.MedicationBlock;
import com.pms.medication_service.model.MedicationInventory;
import org.springframework.stereotype.Service;

@Service
public class InventoryMapper {
    public ExpenseRequest toExpense(MedicationInventoryRequest request) {
        return new ExpenseRequest(
                request.expense_id(),
                request.total_price(),
                request.description(),
                request.farm_id()
        );
    }

    public InventoryCode fromInventory(MedicationInventory feedInventory) {
        return new InventoryCode(feedInventory.getMedication_inventory_code());
    }

    public InventoryResponse fromAllInventory(MedicationInventory medInventory) {
        return new InventoryResponse(
                medInventory.getMedication_inventory_id(),
                medInventory.getMedication_inventory_code(),
                medInventory.getAvailable_quantity(),
                medInventory.getExpense_id(),
                medInventory.getMedicationStorage().getMedication_id(),
                medInventory.getMedicationStorage().getMedication_type().toString(),
                medInventory.getMedicationInventoryCost().getCost_per_unit(),
                medInventory.getMedicationInventoryCost().getMedicationSupplier().getSupplier_name(),
                medInventory.getMedicationInventoryCost().getMedicationSupplier().getSupplier_phone()
        );
    }

    public FarmUiResponse forFarmUi(MedicationInventory medInventory) {
        return new FarmUiResponse(
               medInventory.getMedication_inventory_id(),
               medInventory.getMedicationStorage().getInitial_medication_quantity(),
               medInventory.getAvailable_quantity()
        );
    }

    public BlockDetails toBlockDetails(MedicationBlock medBlock, String placementCode) {
        return new BlockDetails(
                medBlock.getPlacement_id(),
                placementCode,
                medBlock.getBlock_quantity()
        );
    }
}
