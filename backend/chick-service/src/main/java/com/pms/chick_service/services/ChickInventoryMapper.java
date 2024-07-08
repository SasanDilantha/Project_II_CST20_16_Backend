package com.pms.chick_service.services;

import com.pms.chick_service.dto.*;
import com.pms.chick_service.dto.client.ToChickBlockDetails;
import com.pms.chick_service.dto.ui.response.BlockDetails;
import com.pms.chick_service.dto.ui.response.FarmUiResponse;
import com.pms.chick_service.model.ChickBlock;
import com.pms.chick_service.model.ChickInventory;
import com.pms.chick_service.model.ChickInventryCost;
import com.pms.chick_service.model.ChickStorage;
import com.pms.chick_service.repository.ChickInventryCostRepository;
import com.pms.chick_service.repository.ChickStorageRepository;
import org.springframework.stereotype.Service;

@Service
public class ChickInventoryMapper {

    public ChickInventory toInventory(Integer chick_inventory_id,Integer chick_quantity,Integer chickBreedId, Integer chickInventoryCostId, Integer expenseId, String chickInventoryCode, ChickStorageRepository storageRepository, ChickInventryCostRepository costRepository) {
        ChickStorage storage = storageRepository.findById(chickBreedId).orElseThrow(
                ()-> new IllegalArgumentException("Storage not found with id: " + chickBreedId)
        );
        ChickInventryCost cost = costRepository.findById(chickInventoryCostId).orElseThrow(
                () -> new IllegalArgumentException("Cost not found with id: " + chickInventoryCostId)
        );
        return ChickInventory.builder()
                .chick_inventory_id(chick_inventory_id)
                .available_quantity(chick_quantity)
                .chickStorage(storage)
                .chick_inventory_code(chickInventoryCode)
                .chickInventryCost(cost)
                .expense_id(expenseId)
                .build();
    }

    public InvetoryCode fromInventory(ChickInventory chickInventory) {
        return new InvetoryCode(chickInventory.getChick_inventory_code());
    }

    public ExpenseRequest toExpense(ChickInventoryRequest request) {
        return new ExpenseRequest(
                request.expense_id(),
                request.total_price(),
                request.description(),
                request.farm_id()
        );
    }

    public ChickBlock toBlock(BlockRequest request, Integer placementId, ChickStorageRepository storageRepository) {
        ChickStorage storage = storageRepository.findById(request.storage_id()).orElseThrow(
                ()-> new IllegalArgumentException("storage not found with id: " + request.storage_id())
        );
        return ChickBlock.builder()
                .chick_block_id(request.block_id())
                .block_quantity(request.block_quantity())
                .chick_storage(storage)
                .placement_id(placementId)
                .build();
    }

    public InvetoryResponse fromAllInventory(ChickInventory chickInventory) {
        return new InvetoryResponse(
                chickInventory.getChick_inventory_id(),
                chickInventory.getChick_inventory_code(),
                chickInventory.getAvailable_quantity(),
                chickInventory.getExpense_id(),
                chickInventory.getChickStorage().getChick_storage_id(),
                chickInventory.getChickStorage().getBreed().toString(),
                chickInventory.getChickStorage().getAge(),
                chickInventory.getChickInventryCost().getPrice_per_chick(),
                chickInventory.getChickInventryCost().getChickSupplier().getSupplier_name(),
                chickInventory.getChickInventryCost().getChickSupplier().getSupplier_phone()
        );
    }

    public FarmUiResponse forFarmUi(ChickInventory chickInventory) {
        return new FarmUiResponse(
                chickInventory.getChick_inventory_id(),
                chickInventory.getChickStorage().getChick_quantity(),
                chickInventory.getAvailable_quantity(),
                chickInventory.getChickStorage().getAge()
        );
    }

    public BlockDetails toBlockDetails(ChickBlock chickBlock, String placementCode, ToChickBlockDetails mortality) {
        //Integer mortality_count = (mortality.count() == null) ? 0 : mortality.count();
        //String mortality_description = (mortality.description() == null) ? "" : mortality.description();
        return new BlockDetails(
                chickBlock.getPlacement_id(),
                placementCode,
                chickBlock.getBlock_quantity(),
                mortality.count(),
                mortality.description()
        );
    }

    public ChickBlockTest test(ChickBlock chickBlock) {
        return new ChickBlockTest(
                chickBlock.getChick_block_id(),
                chickBlock.getPlacement_id(),
                chickBlock.getBlock_quantity(),
                chickBlock.getChick_storage().getChick_storage_id()
        );
    }

}
