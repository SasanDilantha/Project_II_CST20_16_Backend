package com.pms.medication_service.services;


import com.pms.medication_service.dto.BlockRequest;
import com.pms.medication_service.dto.MedicationInventoryRequest;
import com.pms.medication_service.model.*;
import com.pms.medication_service.repository.MedicationInventoryCostRepository;
import com.pms.medication_service.repository.MedicationStorageRepository;
import org.springframework.stereotype.Service;

@Service
public class MeditationMapper {
    public MedicationStorage toStorage(MedicationInventoryRequest request) {
        return MedicationStorage.builder()
                .medication_id(request.med_storage_id())
                .medication_type(request.med_type())
                .initial_medication_quantity(request.med_quantity())
                .build();
    }

    public MedicationInventory toInventory(
            MedicationInventoryRequest request,
            Integer feedId,
            Integer feedInventoryCostId,
            Integer expenseId,
            String medInventoryCode,
            MedicationStorageRepository storageRepository,
            MedicationInventoryCostRepository inventoryCostRepository
    ) {
        MedicationStorage storage = storageRepository.findById(feedId).orElseThrow(
                ()-> new IllegalArgumentException("Storage not found with id: " + feedId)
        );
        MedicationInventoryCost cost = inventoryCostRepository.findById(feedInventoryCostId).orElseThrow(
                () -> new IllegalArgumentException("Cost not found with id: " + feedInventoryCostId)
        );
        return MedicationInventory.builder()
                .medication_inventory_id(request.med_inventory_id())
                .available_quantity(request.med_quantity())
                .medicationStorage(storage)
                .medication_inventory_code(medInventoryCode)
                .medicationInventoryCost(cost)
                .expense_id(expenseId)
                .build();
    }

    public MedicationBlock toBlock(BlockRequest request, Integer placementId, MedicationStorageRepository storageRepository) {
        MedicationStorage storage = storageRepository.findById(request.storage_id()).orElseThrow(
                ()-> new IllegalArgumentException("storage not found with id: " + request.storage_id())
        );
        return MedicationBlock.builder()
                .medication_block_id(request.block_id())
                .block_quantity(request.block_quantity())
                .medication_storage(storage)
                .placement_id(placementId)
                .build();
    }
}
